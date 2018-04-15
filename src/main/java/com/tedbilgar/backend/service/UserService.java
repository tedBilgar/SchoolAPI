package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Item;
import com.tedbilgar.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> findAll();
    public String findUser();
    public User findFUser();

    public User findUserByEmail(String email);
    public void saveUser(User user);
    public boolean initUser(User user);
    public String getUserNameByEmail(String email);
    public int getScoreByEmail(String email);
    public int setScoreByEmail(String email,int score);
    public int setItemByName(String email,String itemName);
    public Set<Item> getItemsByEmail(String email);

    //Level grade
    public void setLevelGradeByEmailAndHero(String email,String heroName,String levelGrade);
    public String getLevelGrade (String email,String heroName);
}
