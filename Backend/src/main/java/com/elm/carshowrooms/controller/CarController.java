package com.elm.carshowrooms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.elm.carshowrooms.DTO.CarDTO;
import com.elm.carshowrooms.DTO.CarShowRoomDTO;
import com.elm.carshowrooms.model.Car;
import com.elm.carshowrooms.service.CarService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
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


    @GetMapping("/carsWithShowRoom")
    public List<CarDTO> listCarsWithShowroomDetails(@RequestParam(value = "vin", required = false) String vin, @RequestParam(value = "maker", required = false) String maker,
    @RequestParam(value = "model", required = false) String model, @RequestParam(value = "model_year", required = false) Integer model_year,
    @RequestParam(value = "price", required = false) Double price, @RequestParam(value = "carShowroomID", required = false) Long carShowroomID, Pageable pageable) {
    
        List<CarDTO> carsDTO = carService.getAllCarsWithShowRoomDetails(vin, maker, model, model_year, price, carShowroomID, pageable);
         
         return carsDTO;
    }


    
}