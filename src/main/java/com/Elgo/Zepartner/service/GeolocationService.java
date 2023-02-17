package com.Elgo.Zepartner.service;

import com.Elgo.Zepartner.exceptions.PartnerNotFoundException;
import com.Elgo.Zepartner.model.KDtree.LocationKDTree;
import com.Elgo.Zepartner.model.Partner;
import com.Elgo.Zepartner.repository.Geolocation.GeolocationLocationRepository;
import com.Elgo.Zepartner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ToString
@Slf4j
@AllArgsConstructor
@Service

@Configuration
public class GeolocationService {

    @Autowired
    private PartnerRepository partnerRepository;

    //private GeolocationLocationRepository geolocationLocationRepository = new GeolocationLocationRepository();

    @Bean
    GeolocationLocationRepository Intial()
    {
        GeolocationLocationRepository geolocationLocationRepository = new GeolocationLocationRepository();
        return geolocationLocationRepository;
    }


    public void save(Partner partner)
    {
        partnerRepository.save(partner);
    }

    @Transactional(readOnly = true)
    public List<Partner> getAllPartners()
    {
        return partnerRepository.findAll();
    }

   // private Long Id;
    @Transactional(readOnly = true)
    public Partner loadUserByPartnerId (Long id)  {
        //this.Id=id;
        Partner partner=partnerRepository.findById(id)
                .orElseThrow(() -> new PartnerNotFoundException("No partner with that id"));//Id.toString()));
        return partner;
    }

    public Partner findNearestPoint(double lat, double lon) {

        GeolocationLocationRepository geolocationLocationRepository=Intial();

        LocationKDTree points = geolocationLocationRepository.findAllPoints();

        Partner NearestPartner=points.findNearest(lat, lon);
        return NearestPartner;
    }


}
