package com.tedbilgar.backend.model;

import org.springframework.http.HttpStatus;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

public class UserItems {
    private Set<Item> userItemList;

    public Set<Item> getUserItemList() {
        return userItemList;
    }

    public void setUserItemList(Set<Item> userItemList) {
        this.userItemList = userItemList;
    }
}
