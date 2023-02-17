package com.Elgo.Zepartner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Address {
    protected String type;
    protected double latitude;
    protected double longitude;
}
