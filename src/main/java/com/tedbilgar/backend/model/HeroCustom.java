package com.tedbilgar.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
//@Data
@Table(name = "Hero_Custom")
public class HeroCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hero_customId")
    private int id;

    @Column(name = "user_id1")
    private int user_id;

    @Column(name = "hero_id1")
    private int hero_id;

    @Column(name = "level_path")
    private String levelPath;

    @Column(name = "tree_path")
    private String treePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public String getLevelPath() {
        return levelPath;
    }

    public void setLevelPath(String levelPath) {
        this.levelPath = levelPath;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}
