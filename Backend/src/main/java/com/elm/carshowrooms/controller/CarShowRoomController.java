package com.elm.carshowrooms.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elm.carshowrooms.DTO.CarShowRoomDTO;
import com.elm.carshowrooms.DTO.PaginatedCarShowroomDTO;
import com.elm.carshowrooms.DTO.UpdateCarShowRoomDTO;
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

    @PutMapping(value ="/{id}")
    public ResponseEntity updateCarShowRoomById(@PathVariable("id") Long id, @RequestBody Map<String, Object> newCarShowRoom) {
        
        UpdateCarShowRoomDTO updateCarShowRoomDTO = new UpdateCarShowRoomDTO();

        List<String> allowedFields = Arrays.asList("contact_number", "manager_name", "address");
        for (String key : newCarShowRoom.keySet()) {
            if (!allowedFields.contains(key)) {
              
                return ResponseEntity.badRequest().build();
            }else{
                
                if (key.equalsIgnoreCase("contact_number" ) && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == int.class){
                    updateCarShowRoomDTO.setContactNumber( (int) newCarShowRoom.get(key));
                }
                else if (key.equalsIgnoreCase("manager_name" ) && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == String.class){
                    updateCarShowRoomDTO.setManagerName((String) newCarShowRoom.get(key) );
                }
                else if (key.equalsIgnoreCase("address" ) && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == String.class){
                    updateCarShowRoomDTO.setAddress((String) newCarShowRoom.get(key));
                }
            }

        }


        carShowRoomService.updateCarShowRoom(id, updateCarShowRoomDTO);

        return ResponseEntity.noContent().build();
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteShowroom(@PathVariable("id") Long id) {
        carShowRoomService.softDeleteCarShowRoom(id);
        return ResponseEntity.noContent().build();
    }
        
}

