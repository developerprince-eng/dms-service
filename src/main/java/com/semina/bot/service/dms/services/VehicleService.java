package com.semina.bot.service.dms.services;

import com.semina.bot.service.dms.dto.VehicleDto;
import com.semina.bot.service.dms.exceptions.NoAvailableResourceException;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Vehicle;
import com.semina.bot.service.dms.repository.VehicleRepository;
import org.modelmapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VehicleService {
    Logger log = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    VehicleRepository vehicleRepository;


    public Vehicle retrieveVehicleById(String vehicleId){
        Vehicle result = new Vehicle();
        try {
           result =  vehicleRepository.findByVehicleID(vehicleId);
           log.info("Successfully retrieved vehicle {} with ID {} " , result, vehicleId);
        }catch (NoSuchResourceException ex){
            log.warn(" No Such Vehicle by ID {} {}", vehicleId, ex.getLocalizedMessage());
        }catch (Exception ex){
            log.error("Error : ", ex);
        }
        return result;
    }

    public Vehicle retrieveVehicleByVin(String vehicleVin){
        Vehicle result = new Vehicle();
        try {
            result =  vehicleRepository.findByVehicleVin(vehicleVin);
            log.info("Successfully retrieved vehicle {} with VIN {} " , result, vehicleVin);
        }catch (NoSuchResourceException ex){
            log.warn(" No Such Vehicle by VIN {} {}", vehicleVin, ex.getLocalizedMessage());
        }catch (Exception ex){
            log.error("Error : ", ex);
        }
        return result;
    }

    public List<Vehicle> retrieveAllVehicles(){
        List<Vehicle> resultList = new ArrayList<>();
        try {
            resultList = vehicleRepository.findAll();
            log.info("Successfully retrieved all Vehicles : {}", resultList);
        }catch (NoSuchResourceException ex){
            log.warn("No Vehicles Available: {}", ex.getLocalizedMessage());
        }
        return resultList;
    }

    public void editVehicle(VehicleDto vehicleDto){
        Vehicle result;
        try{
            log.info("The DTO: {}", vehicleDto);
            Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
            if(vehicleRepository.findByVehicleID(vehicle.getVehicleID()) != null){
                result = vehicleRepository.save(vehicle);
                log.info("Mapped Vehicle Object: {}",result);
                log.info("Edited Vehicle Successful: {}", vehicle);
            }
        }catch (NoAvailableResourceException ex){
            log.warn("No Such Vehicle Present: {}", ex.getLocalizedMessage());
        }
    }

    public void addNewVehicle(String customerId,VehicleDto vehicleDto){
        Vehicle result;
        try{
            log.info("The DTO: {}", vehicleDto);
            Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
            log.info("Mapped Vehicle Object: {}", vehicle);
            result = vehicleRepository.save(vehicle);
            vehicleRepository.saveInForeign(customerId, vehicle.getVehicleID());
            log.info("Edited Vehicle Successful: {}", result);


        }catch (NoAvailableResourceException ex){
            log.warn("No Such Vehicle Present: {}", ex.getLocalizedMessage());
        }

    }

    public void removeVehicle(String vehicleId){
        try {
            vehicleRepository.deleteByVehicleID(vehicleId);
            log.info("Successfully Deleted Vehicle with ID: {}", vehicleId);
        }catch (NoSuchResourceException ex){
            log.warn("Error in Deleting Vehicle By ID: {}", ex.getLocalizedMessage());
        }
    }


}
