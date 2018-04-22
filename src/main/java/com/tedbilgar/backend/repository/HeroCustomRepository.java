package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.Hero;
import com.tedbilgar.backend.model.HeroCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HeroCustomRepository extends JpaRepository<HeroCustom,Long>{
    HeroCustom findHeroCustomByUserIdAndHeroId(int userId,int heroId);
    HeroCustom findHeroCustomById(int id);

    Set<HeroCustom> findHeroCustomsByUserId(int userId);
}
