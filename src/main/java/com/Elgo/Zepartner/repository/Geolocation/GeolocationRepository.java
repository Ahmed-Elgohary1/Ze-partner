package com.Elgo.Zepartner.repository.Geolocation;

import com.Elgo.Zepartner.model.KDtree.LocationKDTree;

import java.util.List;

public interface GeolocationRepository {
    LocationKDTree findAllPoints();


}
