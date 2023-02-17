package com.Elgo.Zepartner.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class CoverageArea {


    private String flavor;

    //private int[][] coordinates = new int[6][2];

    @OneToMany(
           targetEntity = Coordinates.class,
            cascade = CascadeType.ALL
    )

//    @JoinColumn(
//           name = "coordinateId",
//            referencedColumnName = "coordinateId"
//   )

  private List<Coordinates> coordinates;

}
