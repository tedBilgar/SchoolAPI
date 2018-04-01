package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Item;
import com.tedbilgar.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemByName(String name) {
        return itemRepository.findItemByItemName(name);
    }
}
