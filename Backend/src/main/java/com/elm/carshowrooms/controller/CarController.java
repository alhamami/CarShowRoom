package com.elm.carshowrooms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elm.carshowrooms.DTO.CarDTO;
import com.elm.carshowrooms.service.CarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid; 

@RestController

public class CarController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Add new car to show room", description = "Add new car to a specified show room by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Car created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or car already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/showrooms/{carShowRoomId}/cars")
    public ResponseEntity<?> saveCar(@Parameter(description = "ID of show room to add car to it") @PathVariable Long carShowRoomId, @Valid  @RequestBody CarDTO newCar) {

        try{


            CarDTO savedCar = carService.saveCar(carShowRoomId, newCar);
            if(savedCar.getVin() == null){
                return ResponseEntity.status(400).body("There is no Car Show Room with Commercial Registration Number ["+ carShowRoomId+"].");
            }else if(savedCar.getVin().equals("exist")){
                return ResponseEntity.status(400).body("Car with vin ["+ newCar.getVin()+"] already exists.");
            }else{
                    return ResponseEntity.status(201).build();
            }

        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There Is Constraint Violation: " + exception.getMessage());
        }
    }


    @Operation(summary = "List all cars with show room details", description = "Get list of cars along with their show room details based on search filters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of cars retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/carsWithShowRoom")
    public List<CarDTO> listCarsWithShowroomDetails( @Parameter(description = "VIN of car") @RequestParam(value = "vin", required = false) String vin,  @Parameter(description = "Maker of car") @RequestParam(value = "maker", required = false) String maker,
    @Parameter(description = "Model of car")  @RequestParam(value = "model", required = false) String model, @Parameter(description = "Model year of car") @RequestParam(value = "model_year", required = false) Integer model_year,
    @Parameter(description = "Price of car") @RequestParam(value = "price", required = false) Double price, @Parameter(description = "Car Show Room ID of car") @RequestParam(value = "carShowroomID", required = false) Long carShowroomID, Pageable pageable) {
    
        List<CarDTO> carsDTO = carService.getAllCarsWithShowRoomDetails(vin, maker, model, model_year, price, carShowroomID, pageable);
         
         return carsDTO;
    }


    
}