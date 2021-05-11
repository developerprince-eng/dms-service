package com.semina.bot.service.dms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String customerID;

    private String mobilePhone;

    private String address;

    private Integer zipCode;

    private Short equifax;

    private Short transunion;

    private  Short experian;

    private String employmentStatus;

    private String employmentName;

    private String employmentPosition;

    private String employmentStartDate;

    private String employmentEndDate;

    private String email;

    private String givenName;

    private String lastName;

    private String maritalStatus;

    private String educationalLevel;

    private String gender;

    private String county;

    private String state;

}
