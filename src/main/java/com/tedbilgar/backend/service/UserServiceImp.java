package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Item;
import com.tedbilgar.backend.model.Role;
import com.tedbilgar.backend.model.User;
import com.tedbilgar.backend.repository.ItemRepository;
import com.tedbilgar.backend.repository.RoleRepository;
import com.tedbilgar.backend.repository.UserRepository;
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userService")
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // API functions
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String findUser() {
        return userRepository.findUserById(1).getEmail();
    }

    @Override
    public User findFUser(){
        return userRepository.findUserById(1);
    }

    @Override
    public String getUserNameByEmail(String email){
        return userRepository.findUserByEmail(email).getName();
    }

    @Override
    public int getScoreByEmail(String email){
        return userRepository.findUserByEmail(email).getScore();
    }

    @Override
    public int setScoreByEmail(String email,int score){
        User userExists = userRepository.findUserByEmail(email);
        if(userExists == null) return 0;
        userExists.setScore(userExists.getScore()+score);
        userRepository.save(userExists);
        return 1;
    }
    @Override
    public int setItemByName(String email,String itemName){
        User userExists = userRepository.findUserByEmail(email);
        if(userExists == null) return 0;
        Item userItem = itemRepository.findItemByItemName(itemName);
        userExists.setItems(new HashSet<Item>(Arrays.asList(userItem)));
        userRepository.save(userExists);
        return 1;
    }

    @Override
    public Set<Item> getItemsByEmail(String email) {
        return userRepository.findUserByEmail(email).getItems();
    }

    // Essantial funcs
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public boolean initUser(User user) {
        User userExists = userRepository.findUserByEmail(user.getEmail());
       // user.setPassword(new BCryptPasswordEncoder().matches(user.getPassword(),userExists.getPassword()));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (userExists !=null){
            if(encoder.matches(user.getPassword(),userExists.getPassword())){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
    }
}
