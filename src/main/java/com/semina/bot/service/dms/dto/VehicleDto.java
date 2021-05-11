package com.semina.bot.service.dms.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehicleDto {

    private String vehicleID;

    private String vehicleMake;

    private String vehicleVin;

    private String vehicleTrim;

    private String vehicleModel;

    private String vehicleColor;

    private Double vehiclePurchasePrice;

    private Integer vehicleModelYear;

    private String purchaseDate;

    private Integer termLength;

    private String maturityDate;
}
