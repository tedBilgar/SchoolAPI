package com.tedbilgar.backend.service;

import com.tedbilgar.backend.model.Location;

import java.util.List;

public interface LocationService {
    public List<Location> findAllLocations();

    public Location findLocationByName(String name);
}
