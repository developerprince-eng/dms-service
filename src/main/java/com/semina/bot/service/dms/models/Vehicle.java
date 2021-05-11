package com.semina.bot.service.dms.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Data
@Entity
@Table(name = "vehicle_table")
public class Vehicle {

    @Id
    @Column(columnDefinition = "VARCHAR(12)")
    private String vehicleID;

    @Column(columnDefinition = "VARCHAR(198)")
    private String vehicleMake;

    @Column(columnDefinition = "VARCHAR(17)")
    private String vehicleVin;

    @Column(columnDefinition = "VARCHAR(6)")
    private String vehicleTrim;

    @Column(columnDefinition = "VARCHAR(32)")
    private String vehicleModel;

    @Column(columnDefinition = "VARCHAR(32)")
    private String vehicleColor;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private Double vehiclePurchasePrice;

    @Column(columnDefinition = "INT")
    private Integer vehicleModelYear;


    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate purchaseDate;

    @Column(columnDefinition = "SMALLINT")
    private Integer termLength;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate maturityDate;


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Vehicle)) return false;
        return vehicleID != null && vehicleID.equals(((Vehicle) o).getVehicleID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
