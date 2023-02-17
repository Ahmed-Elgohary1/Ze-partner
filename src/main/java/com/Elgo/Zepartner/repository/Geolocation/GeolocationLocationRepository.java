package com.Elgo.Zepartner.repository.Geolocation;

import com.Elgo.Zepartner.model.KDtree.LocationKDTree;
import com.Elgo.Zepartner.model.Partner;
import com.Elgo.Zepartner.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;

public class GeolocationLocationRepository {

    @Autowired
    private PartnerRepository partnerRepository;

    Logger logger = LoggerFactory.getLogger(GeolocationLocationRepository.class);

    public LocationKDTree findAllPoints() {

        List<Partner> partners = partnerRepository.findAll();

        Assert.notNull(partners, "The points must not be null");

        return new LocationKDTree(partners);
    }


}
