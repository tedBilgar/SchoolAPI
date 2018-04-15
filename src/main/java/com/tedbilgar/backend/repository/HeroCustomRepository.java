package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.Hero;
import com.tedbilgar.backend.model.HeroCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroCustomRepository extends JpaRepository<HeroCustom,Long> {
    @Override
    HeroCustom findOne(Long aLong);

    //HeroCustom findHeroCustomBy
}
