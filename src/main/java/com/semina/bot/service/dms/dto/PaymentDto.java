package com.semina.bot.service.dms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentDto {
    private String paymentID;

    private Double balance;

    private String paymentDate;

    private Double paymentAmount;

    private String payerName;

    private String payerMobilePhone;

    private String payerLastName;

    private String payerSSN;

    private String relationship;

    private String paymentType;

    private Double paymentDue;

    private LocalDateTime paymentDueDate;

    private LocalDateTime lastPaymentMadeDate;

    private Short defaultedPayments;

    private List<VehicleDto> vehicles = new ArrayList<>();
}
