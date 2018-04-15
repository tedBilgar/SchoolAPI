package com.tedbilgar.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Hero_Custom")
public class HeroCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hero_customId")
    private int id;

    @Column(name = "user_id1")
    int user_id;

    @Column(name = "hero_id1")
    int hero_id;

    @Column(name = "level_path")
    String levelPath;

    @Column(name = "tree_path")
    String treePath;
}
