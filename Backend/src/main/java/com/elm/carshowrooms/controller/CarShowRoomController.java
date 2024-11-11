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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elm.carshowrooms.DTO.CarShowRoomDTO;
import com.elm.carshowrooms.DTO.PaginatedCarShowroomDTO;
import com.elm.carshowrooms.service.CarShowRoomService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/showrooms")
public class CarShowRoomController {
    @Autowired
    private CarShowRoomService carShowRoomService;

    @PostMapping
    public ResponseEntity<?> saveShowRoom(@Valid @RequestBody CarShowRoomDTO carShowRoom) {

        try{

            CarShowRoomDTO savedCarShowRoom = carShowRoomService.saveShowRoom(carShowRoom);


            if(savedCarShowRoom.getCommercialRegistrationNumber() == null){
                return ResponseEntity.status(400).body("CarShowRoom with Commercial Registration Number ["+ carShowRoom.getCommercialRegistrationNumber()+"] already exists.");
            }else{
                    return ResponseEntity.status(201).build();
            }

        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There Is Constraint Violation: " + exception.getMessage());
        }

        
        
    }

    @GetMapping
    public ResponseEntity<List<PaginatedCarShowroomDTO>> getAllShowrooms(Pageable pageable) {
        List<PaginatedCarShowroomDTO> listOfCarShowRoom = carShowRoomService.getAllShowrooms(pageable);
        return ResponseEntity.ok(listOfCarShowRoom);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CarShowRoomDTO> getCarShowroomById(@PathVariable("id") Long id) {
        CarShowRoomDTO carShowRoom = carShowRoomService.getCarShowRoomById(id);
         return carShowRoom.getName() == null? ResponseEntity.notFound().build(): ResponseEntity.ok(carShowRoom);
    }
}

