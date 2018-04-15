package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.Hero;
import com.tedbilgar.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero,Long> {
    Hero findHeroByHeroName(String heroName);
}
