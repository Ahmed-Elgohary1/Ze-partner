package com.Elgo.Zepartner.repository;

import com.Elgo.Zepartner.model.Address;
import com.Elgo.Zepartner.model.Coordinates;
import com.Elgo.Zepartner.model.CoverageArea;
import com.Elgo.Zepartner.model.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void savePartner()
    {
        Coordinates coordinates=Coordinates.builder()
                .x(5)
                .y(4)
                .build();
        List<Coordinates>p=new ArrayList<Coordinates>();
        p.add(coordinates);
        CoverageArea coverageArea=CoverageArea.builder()
                .coordinates(p)
                .flavor("point")
                .build();

        Address address=Address.builder()
                .type("poly")
                .longitude(20.5)
                .latitude(15.3)
                .build();

        Partner partner=Partner.builder()
                .ownerName("Ahmed")
                .address(address)
                .coverageArea(coverageArea)
                .tradingName("Elgo")
                .build();

        partnerRepository.save(partner);


    }

}