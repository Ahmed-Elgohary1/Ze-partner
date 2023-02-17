package com.Elgo.Zepartner.controller;


import com.Elgo.Zepartner.dto.CoordToSearch;
import com.Elgo.Zepartner.model.Partner;
import com.Elgo.Zepartner.repository.PartnerRepository;
import com.Elgo.Zepartner.service.GeolocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/partners/")
@AllArgsConstructor
public class PartnerController {

    private final GeolocationService geolocationService;
    private final PartnerRepository partnerRepository;

    @PostMapping
    public ResponseEntity<Void> createPartner(@RequestBody Partner partner)
    {
        geolocationService.save(partner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Partner>> getAllPartners()
    {
        return status(HttpStatus.OK).body(geolocationService.getAllPartners());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Partner> getPartner(@PathVariable Long id)
    {
        return status(HttpStatus.OK).body(geolocationService.loadUserByPartnerId(id));
    }

    @GetMapping("/{search}")
    public ResponseEntity<Partner> getPartner(@PathVariable CoordToSearch coordToSearch)
    {
        return status(HttpStatus.OK).body(geolocationService.findNearestPoint(coordToSearch.getLat(),coordToSearch.getLongt()));
    }

}
