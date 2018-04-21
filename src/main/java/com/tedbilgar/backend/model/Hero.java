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

    @Column(name = "hero_name")
    private String heroName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
