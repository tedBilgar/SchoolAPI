package com.tedbilgar.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column
    private String itemName;

}
