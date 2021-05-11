package com.semina.bot.service.dms.controllers;

import com.semina.bot.service.dms.dto.PaymentDto;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Payment;
import com.semina.bot.service.dms.services.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Tag(name = "Payment Operations for DMS")
@RestController
@RequestMapping("api/v1/payment")
@Api(value = "Payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/retrieve/mobile/{mobile}")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieved Payment/s by mobile Number"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Payment> getPaymentByMobile(@PathVariable("mobile") String mobilePhone){
        Payment response;
        try {
            response = paymentService.retrievePaymentByMobile(mobilePhone);
            if(response == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Make new Payment for Vehicle")
    @PostMapping("/make/payment/{id}")
    @ApiResponses(
            value={
                    @ApiResponse(code=201, message = "Successfully made a New Payment"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code=400, message = "Bad Request"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Payment> makePayment(@PathVariable("id") String vehicleId,@RequestBody PaymentDto paymentDto){
        try {
            paymentService.makeNewPayment(vehicleId ,paymentDto);
        }catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Revoke Payment Using Mobile, This is Done by Administrator")
    @DeleteMapping("/revoke/{mobile}")
    @ApiResponses(
            value={
                    @ApiResponse(code=204, message = "Successfully deleted Payment using mobile"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Payment> revokePayment(@PathVariable("mobile") String mobilePhone){
        try {
            paymentService.removePayment(mobilePhone);
        } catch (NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
