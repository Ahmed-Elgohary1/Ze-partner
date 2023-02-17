package com.Elgo.Zepartner.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    private String tradingName;
    private String ownerName;
   @Embedded
    private CoverageArea coverageArea;
    @Embedded
    private Address address;


}


