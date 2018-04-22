package com.tedbilgar.backend.model;

import java.util.Set;

public class UserHeroes {
    private Set<HeroCustom> userHeroes;

    public Set<HeroCustom> getUserHeroes() {
        return userHeroes;
    }

    public void setUserHeroes(Set<HeroCustom> userHeroes) {
        this.userHeroes = userHeroes;
    }
}
