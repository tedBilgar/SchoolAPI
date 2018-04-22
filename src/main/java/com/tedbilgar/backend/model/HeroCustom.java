package com.tedbilgar.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Hero_Custom")
public class HeroCustom {
    @Id
    @Column(name = "hero_customId")
    private int id;

    @Column(name = "user_id1")
    private int userId;

    @Column(name = "hero_id1")
    private int heroId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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
