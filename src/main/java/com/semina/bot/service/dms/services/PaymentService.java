package com.semina.bot.service.dms.services;

import com.semina.bot.service.dms.dto.PaymentDto;
import com.semina.bot.service.dms.exceptions.NoAvailableResourceException;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Payment;
import com.semina.bot.service.dms.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;

    public Payment retrievePaymentByMobile(String mobile){
        Payment result = new Payment();
        try {
            result = paymentRepository.findByPayerMobilePhone(mobile);
            log.info("Successfully Retrieved Payment using {} : {}", mobile, result);
        }catch (NoSuchResourceException ex){
            log.error("No payment made by {} : ", mobile, ex.fillInStackTrace());
        }
        return result;
    }

    public void makeNewPayment(String vehicleId,PaymentDto paymentDto){
        Payment result;
        try{
            log.info("The DTO: {}", paymentDto);
            Payment payment = modelMapper.map(paymentDto, Payment.class);
            log.info("Mapped Payment Object: {}", payment);
            result=  paymentRepository.save(payment);
            paymentRepository.saveInForeign(payment.getPaymentID(), vehicleId);
            log.info("Made a new Payment: {}", result);

        }catch (Exception ex){
            log.error("Error in making payment: ", ex.getCause());
        }
    }

    public void removePayment(String mobilePhone){
        try{
            paymentRepository.deleteByPayerMobilePhone(mobilePhone);
            log.info("Successfully Removed Payment using : {}", mobilePhone);
        }catch (NoAvailableResourceException ex){
            log.warn("No Such Payment associated to {}", mobilePhone);
            log.error("Caused By: ", ex.fillInStackTrace());
        }
    }
}
