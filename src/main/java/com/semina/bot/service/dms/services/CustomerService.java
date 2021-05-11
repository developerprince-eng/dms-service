package com.semina.bot.service.dms.services;

import com.semina.bot.service.dms.dto.CustomerDto;
import com.semina.bot.service.dms.exceptions.NoAvailableResourceException;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Customer;
import com.semina.bot.service.dms.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {
    Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    public Customer retrieveCustomerByMobilePhone(String mobile){
        Customer result = new Customer();
        try{
            result = customerRepository.findByMobilePhone(mobile);
            log.info("Successfully Retrieved Customer: {}", result);
        }catch (NoSuchResourceException ex){
            log.error("No Customer Available: {}", ex.getLocalizedMessage());
        }
        return result;
    }

    public Customer retrieveCustomerID(String id){
        Customer result = new Customer();
        try{
            result = customerRepository.findByCustomerID(id);
            log.info("Successfully Retrieved Customer: {}", result);
        }catch (NoSuchResourceException ex){
            log.error("No Customer Available: {}", ex.getLocalizedMessage());
        }
        return result;
    }

    public Customer addNewCustomer(CustomerDto customerDto){
        Customer result = new Customer();
        try{
            log.info("The DTO: {}", customerDto);
            Customer customer = modelMapper.map(customerDto, Customer.class);
            log.info("Mapped Customer Object: {}", customer);
            result = customerRepository.save(customer);
            log.info("Successfully Added Customer: {} ", result);
        } catch (Exception ex){
            log.error("Error in Adding New Customer with request {} , error caused by : ", customerDto , ex.fillInStackTrace());
        }
        return result;
    }

    public List<Customer> retrieveAllCustomer(){
        List<Customer> resultList = new ArrayList<>();
        try {
            resultList = customerRepository.findAll();
            log.info("Successfully Retrieved All Customers: {}", resultList);
        } catch (NoAvailableResourceException ex){
            log.error("No Available Resource: ", ex.fillInStackTrace());
        } catch (Exception ex){
            log.error("Something Happened: ", ex.fillInStackTrace());
        }
        return resultList;
    }

    public void removeCustomer(String customerID){
        try {
            customerRepository.deleteByCustomerID(customerID);
            log.info("Successfully Deleted Customer with ID {} ", customerID);
        }catch (NoSuchResourceException ex){
            log.error("No Customer by the ID {} : ", customerID, ex.fillInStackTrace());
        }
    }
}
