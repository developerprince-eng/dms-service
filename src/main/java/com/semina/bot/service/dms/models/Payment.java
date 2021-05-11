package com.semina.bot.service.dms.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "payment_table")
public class Payment {

    @Id
    @Column(columnDefinition = "VARCHAR(8)")
    private String paymentID;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private Double balance;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private String paymentDate;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private Double paymentAmount;

    @Column(columnDefinition = "VARCHAR(198)")
    private String payerName;

    @Column(columnDefinition = "VARCHAR(16)")
    private String payerMobilePhone;

    @Column(columnDefinition = "VARCHAR(198)")
    private String payerLastName;

    @Column(columnDefinition = "VARCHAR(9)")
    private String payerSSN;

    @Column(columnDefinition = "VARCHAR(198)")
    private String relationship;

    @Column(columnDefinition = "VARCHAR(198)")
    private String paymentType;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private Double paymentDue;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime paymentDueDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime lastPaymentMadeDate;

    @Column(columnDefinition = "SMALLINT")
    private Short defaultedPayments;

    @ManyToMany()
    private List<Vehicle> vehicles = new ArrayList<>();


}
