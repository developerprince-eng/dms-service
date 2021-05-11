package com.semina.bot.service.dms.controllers;

import com.semina.bot.service.dms.dto.VehicleDto;
import com.semina.bot.service.dms.exceptions.NoAvailableResourceException;
import com.semina.bot.service.dms.exceptions.NoSuchResourceException;
import com.semina.bot.service.dms.models.Vehicle;
import com.semina.bot.service.dms.services.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vehicle Operations for DMS")
@RestController
@RequestMapping("api/v1/vehicle")
@Api(value = "Vehicle Handling")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @ApiOperation(value = "Get a List of All Vehicles")
    @GetMapping
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieved All Vehicles"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<List<Vehicle>> getAllVehicles (){
        List<Vehicle> responseList;
        try{
            responseList = vehicleService.retrieveAllVehicles();
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Vehicle by Vehicle ID")
    @GetMapping("/id/{id}")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieve Vehicles by vehicle ID"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Vehicle> getVehicleByID(@PathVariable("id") String id){
        Vehicle response;
        try{
            response = vehicleService.retrieveVehicleById(id);
            if(response == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Vehicle by Vehicle VIN ")
    @GetMapping("/vin/{vin}")
    @ApiResponses(
            value={
                    @ApiResponse(code=200, message = "Successfully retrieve Vehicle by Vehicle VIN"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable("vin") String vin){
        Vehicle response;
        try {
           response =  vehicleService.retrieveVehicleByVin(vin);
        }catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a New Vehicle associated to Customer")
    @PostMapping("/{id}")
    public ResponseEntity<Vehicle> addNewVehicle(@PathVariable("id") String customerId, VehicleDto vehicleDto){
        try {
            vehicleService.addNewVehicle(customerId, vehicleDto);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Edited Vehicle")
    @PutMapping("/edit-vehicle")
    @ApiResponses(
            value={
                    @ApiResponse(code=204, message = "Successfully edited Vehicle"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Vehicle> editVehicle(@RequestBody VehicleDto vehicle){
        try {
            vehicleService.editVehicle(vehicle);
        }catch (NoAvailableResourceException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete Vehicle by Vehicle ID")
    @DeleteMapping("/{id}")
    @ApiResponses(
            value={
                    @ApiResponse(code=204, message = "Successfully deleted Vehicle"),
                    @ApiResponse(code=401, message = "UnAuthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Not found Resource"),
                    @ApiResponse(code = 500, message = "service error")
            }
    )
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") String vehicleId){
        try {
            vehicleService.removeVehicle(vehicleId);
        }catch (NoSuchResourceException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
