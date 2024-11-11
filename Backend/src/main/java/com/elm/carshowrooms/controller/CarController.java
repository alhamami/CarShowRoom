package com.elm.carshowrooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.elm.carshowrooms.DTO.CarDTO;
import com.elm.carshowrooms.service.CarService;
import jakarta.validation.Valid; 

@RestController

public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/showrooms/{carShowRoomId}/cars")
    public ResponseEntity<?> saveCar(@PathVariable Long carShowRoomId, @Valid  @RequestBody CarDTO newCar) {

        try{


            CarDTO savedCar = carService.saveCar(carShowRoomId, newCar);
            if(savedCar.getVin() == null){
                return ResponseEntity.status(400).body("There is no Car ShowRoom with Commercial Registration Number ["+ carShowRoomId+"].");
            }else if(savedCar.getVin().equals("exist")){
                return ResponseEntity.status(400).body("Car with vin ["+ newCar.getVin()+"] already exists.");
            }else{
                    return ResponseEntity.status(201).build();
            }

        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There Is Constraint Violation: " + exception.getMessage());
        }
    } 
}