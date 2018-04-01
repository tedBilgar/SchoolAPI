package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
