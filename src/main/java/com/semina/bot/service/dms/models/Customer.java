package com.semina.bot.service.dms.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Entity(name= "CustomerTable")
@Table(name = "customer_table")
public class Customer {

    @Id
    @Column(columnDefinition = "VARCHAR(12)")
    private String customerID;

    @Column(columnDefinition = "VARCHAR(14)")
    private String mobilePhone;

    @Column(columnDefinition = "VARCHAR(198)")
    private String address;

    @Column(columnDefinition = "INT")
    private Integer zipCode;

    @Column(columnDefinition = "SMALLINT")
    private Short equifax;

    @Column(columnDefinition = "SMALLINT")
    private Short transunion;

    @Column(columnDefinition = "SMALLINT")
    private  Short experian;

    @Column(columnDefinition = "VARCHAR(96)")
    private String employmentStatus;

    @Column(columnDefinition = "VARCHAR(256)")
    private String employmentName;


    @Column(columnDefinition = "VARCHAR(196)")
    private String employmentPosition;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate employmentStartDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate employmentEndDate;

    @Column(columnDefinition = "VARCHAR(196)")
    private String email;

    @Column(columnDefinition = "VARCHAR(196)")
    private String givenName;

    @Column(columnDefinition = "VARCHAR(198)")
    private String lastName;

    @Column(columnDefinition = "VARCHAR(96)")
    private String maritalStatus;

    @Column(columnDefinition = "VARCHAR(198)")
    private String educationalLevel;

    @Column(columnDefinition = "VARCHAR(12)")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

    private String state;

    private String county;


}
