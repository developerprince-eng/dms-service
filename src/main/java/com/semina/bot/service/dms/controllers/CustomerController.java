package com.semina.bot.service.dms.controllers;

import com.semina.bot.service.dms.dto.CustomerDto;
import com.semina.bot.service.dms.exceptions.NoAvailableResourceException;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Customer;
import com.semina.bot.service.dms.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Operations for DMS")
@RestController
@RequestMapping("api/v1/customer")
@Api(value = "DMS Services")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @ApiOperation(value = "Retrieve All Customer in DMS")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieved All Customers"),
                    @ApiResponse(code=204, message = "Empty Customer Table"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @GetMapping
    public ResponseEntity<List<Customer>> getALLCustomer(){
        List<Customer> responseList;
        try{
            responseList = customerService.retrieveAllCustomer();
        }catch (NoSuchResourceException  ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve Customer By Mobile Phone")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieved Customer by Mobile Phone"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<Customer> getCustomerByMobilePhone(@PathVariable("mobile") String mobilePhone){
        Customer response;
        try{
           response = customerService.retrieveCustomerByMobilePhone(mobilePhone);
           if(response == null){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
        } catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve Customer By Customer ID")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieved Customer ID"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByCustomerID(@PathVariable("id") String customerId){
        Customer response;
        try {
            response = customerService.retrieveCustomerID(customerId);
            if(response == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Adding New Customer This is Done By Administrator")
    @ApiResponses(
            value={
                    @ApiResponse(code=201, message = "Successfully added new Customer"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto){
        Customer response;
        try{
            response = customerService.addNewCustomer(customerDto);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED );
    }

    @ApiOperation(value = "Delete Customer by Customer ID, this is done by Administrator")
    @ApiResponses(
            value={
                    @ApiResponse(code=204, message = "Successfully deleted Customer"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") String customerId){
        try {
            customerService.removeCustomer(customerId);

        }catch (NoAvailableResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Edit Customer Information, this done by Administrator")
    @ApiResponses(
            value={
                    @ApiResponse(code=204, message = "Successfully Updated Customer"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerDto customerDto){
        try {
            if(customerService.retrieveCustomerByMobilePhone(customerDto.getMobilePhone()) != null){
                customerService.addNewCustomer(customerDto);
            }
        }catch (NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
