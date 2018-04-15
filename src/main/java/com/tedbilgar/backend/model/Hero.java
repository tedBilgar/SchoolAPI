package com.tedbilgar.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Hero")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hero_id")
    private int id;

    @Column
    private String heroName;

}
