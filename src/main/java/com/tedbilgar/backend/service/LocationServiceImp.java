package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Location;
import com.tedbilgar.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findLocationByName(String name) {
        return locationRepository.findLocationByName(name);
    }
}
