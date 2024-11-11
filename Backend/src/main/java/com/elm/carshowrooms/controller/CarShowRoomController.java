package com.elm.carshowrooms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elm.carshowrooms.DTO.CarShowRoomDTO;
import com.elm.carshowrooms.DTO.PaginatedCarShowroomDTO;
import com.elm.carshowrooms.DTO.UpdateCarShowRoomDTO;
import com.elm.carshowrooms.service.CarShowRoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/showrooms")
public class CarShowRoomController {

    @Autowired
    private CarShowRoomService carShowRoomService;

    @Operation(summary = "Add new show room", description = "Add new show room to database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Show room added successfully"),
        @ApiResponse(responseCode = "400", description = "Show room with same Commercial Registration Number already exists or constraint violation")
    })
    @PostMapping
    public ResponseEntity<?> saveShowRoom(@Valid @RequestBody CarShowRoomDTO carShowRoom) {
        try {
            CarShowRoomDTO savedCarShowRoom = carShowRoomService.saveShowRoom(carShowRoom);
            if (savedCarShowRoom.getCommercialRegistrationNumber() == null) {
                return ResponseEntity.status(400).body("CarShowRoom with Commercial Registration Number [" + carShowRoom.getCommercialRegistrationNumber() + "] already exists.");
            } else {
                return ResponseEntity.status(201).build();
            }
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There Is Constraint Violation: " + exception.getMessage());
        }
    }

    @Operation(summary = "Get all show rooms", description = "Get paginated list of all car show rooms")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of show rooms retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<PaginatedCarShowroomDTO>> getAllShowrooms(
        @Parameter(description = "Pagination information") Pageable pageable) {
        List<PaginatedCarShowroomDTO> listOfCarShowRoom = carShowRoomService.getAllShowrooms(pageable);
        return ResponseEntity.ok(listOfCarShowRoom);
    }

    @Operation(summary = "Get show room by ID", description = "Get details of specific show room by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show room details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Show room not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarShowRoomDTO> getCarShowroomById(
        @Parameter(description = "ID of the show room to retrieve") @PathVariable("id") Long id) {
        CarShowRoomDTO carShowRoom = carShowRoomService.getCarShowRoomById(id);
        return carShowRoom.getName() == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(carShowRoom);
    }

    @Operation(summary = "Update show room by ID", description = "Update the contact number and manager name and address of specific show room")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Show room updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid field in the request body")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarShowRoomById(
        @Parameter(description = "ID of the show room to update") @PathVariable("id") Long id,
        @RequestBody Map<String, Object> newCarShowRoom) {

        UpdateCarShowRoomDTO updateCarShowRoomDTO = new UpdateCarShowRoomDTO();
        List<String> allowedFields = Arrays.asList("contact_number", "manager_name", "address");
        for (String key : newCarShowRoom.keySet()) {
            if (!allowedFields.contains(key)) {
                return ResponseEntity.badRequest().build();
            } else {
                if (key.equalsIgnoreCase("contact_number") && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == int.class) {
                    updateCarShowRoomDTO.setContactNumber((int) newCarShowRoom.get(key));
                } else if (key.equalsIgnoreCase("manager_name") && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == String.class) {
                    updateCarShowRoomDTO.setManagerName((String) newCarShowRoom.get(key));
                } else if (key.equalsIgnoreCase("address") && newCarShowRoom.get(key) != null && newCarShowRoom.get(key).getClass() == String.class) {
                    updateCarShowRoomDTO.setAddress((String) newCarShowRoom.get(key));
                }
            }
        }

        carShowRoomService.updateCarShowRoom(id, updateCarShowRoomDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Soft delete show room by ID", description = "Make show room as deleted without removing it from database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Show room deleted successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteShowroom(
        @Parameter(description = "ID of show room to delete") @PathVariable("id") Long id) {
        carShowRoomService.softDeleteCarShowRoom(id);
        return ResponseEntity.noContent().build();
    }
}
