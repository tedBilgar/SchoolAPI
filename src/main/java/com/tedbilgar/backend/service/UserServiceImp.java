package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.*;
import com.tedbilgar.backend.repository.*;
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
    private HeroRepository heroRepository;
    @Autowired
    private HeroCustomRepository heroCustomRepository;
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
        return userRepository.findUserByEmail(email).getUsername();
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

    //Methods for Level Grade
    @Override
    public void setLevelGradeByEmailAndHero(String email, String heroName, String levelGrade) {
        int user_id = userRepository.findUserByEmail(email).getId();
        int hero_id = heroRepository.findHeroByHeroName(heroName).getId();
        HeroCustom heroCustomExists = heroCustomRepository.findHeroCustomByUserIdAndHeroId(user_id,hero_id);
        if (heroCustomExists != null){
            heroCustomExists.setLevelPath(levelGrade);
            heroCustomRepository.save(heroCustomExists);
        }else{
            heroCustomExists = new HeroCustom();
            long b = heroCustomRepository.count();
            heroCustomExists.setId((int)b+1);
            heroCustomExists.setUserId(user_id);
            heroCustomExists.setHeroId(hero_id);
            heroCustomExists.setLevelPath(levelGrade);
            heroCustomRepository.save(heroCustomExists);
        }
    }

    @Override
    public void setTreeGradeByEmailAndHero(String email, String heroName, String treeGrade) {
        int user_id = userRepository.findUserByEmail(email).getId();
        int hero_id = heroRepository.findHeroByHeroName(heroName).getId();
        HeroCustom heroCustomExists = heroCustomRepository.findHeroCustomByUserIdAndHeroId(user_id,hero_id);
        if (heroCustomExists != null){
            heroCustomExists.setTreePath(treeGrade);
            heroCustomRepository.save(heroCustomExists);
        }else{
            heroCustomExists = new HeroCustom();
            long b = heroCustomRepository.count();
            heroCustomExists.setId((int)b+1);
            heroCustomExists.setUserId(user_id);
            heroCustomExists.setHeroId(hero_id);
            heroCustomExists.setTreePath(treeGrade);
            heroCustomRepository.save(heroCustomExists);
        }
    }


    @Override
    public HeroCustom getHeroCustom(String email, String heroName) {
        int user_id = userRepository.findUserByEmail(email).getId();
        int hero_id = heroRepository.findHeroByHeroName(heroName).getId();
        return heroCustomRepository.findHeroCustomByUserIdAndHeroId(user_id,hero_id);
    }

    @Override
    public HeroCustom getHeroCustomById (int id){
        return heroCustomRepository.findHeroCustomById(id);
    }

    @Override
    public Set<HeroCustom> getHeroesCustomsByUserEmail(String email) {
        return heroCustomRepository.findHeroCustomsByUserId(userRepository.findUserByEmail(email).getId());
    }

    @Override
    public List<User> findUsersByActiveOrderByScoreDesc(boolean active) {
        return userRepository.findUsersByActiveOrderByScoreDesc(active);
    }

    // Essential funcs
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
