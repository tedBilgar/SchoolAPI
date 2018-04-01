package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Item;

import java.util.List;

public interface ItemService {
    public List<Item> findAllItems();

    public Item findItemByName(String name);
}
