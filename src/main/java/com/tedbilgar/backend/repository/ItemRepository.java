package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findItemByItemName(String name);

    @Override
    List<Item> findAll();
}
