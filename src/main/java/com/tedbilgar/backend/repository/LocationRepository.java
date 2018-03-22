package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer>{
    Location findLocationByName(String name);

    @Override
    List<Location> findAll();
}
