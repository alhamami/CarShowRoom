package com.elm.carshowrooms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.elm.carshowrooms.DTO.CarShowRoomDTO;
import com.elm.carshowrooms.DTO.PaginatedCarShowroomDTO;
import com.elm.carshowrooms.model.CarShowRoom;
import com.elm.carshowrooms.repository.CarShowRoomRepo;

@Service
public class CarShowRoomService {

    @Autowired
    private CarShowRoomRepo carShowRoomRepo;


    public CarShowRoomDTO saveShowRoom(CarShowRoomDTO showRoom) {


        Optional<CarShowRoom> checkCarShowRoom = carShowRoomRepo.findById(showRoom.getCommercialRegistrationNumber());

        

        if (checkCarShowRoom.isPresent()) {
           
            return new CarShowRoomDTO();
           
        } else {
            CarShowRoom newCarShowRoom = new CarShowRoom();
     
            newCarShowRoom.setName(showRoom.getName());
            newCarShowRoom.setCommercialRegistrationNumber(showRoom.getCommercialRegistrationNumber());
            newCarShowRoom.setManagerName(showRoom.getManagerName());
            newCarShowRoom.setContactNumber(showRoom.getContactNumber());
            newCarShowRoom.setAddress(showRoom.getAddress());
    
        
            CarShowRoom carShowRoom = carShowRoomRepo.save(newCarShowRoom);
            return new CarShowRoomDTO( carShowRoom.getName(), carShowRoom.getCommercialRegistrationNumber(), carShowRoom.getManagerName(), carShowRoom.getContactNumber(), carShowRoom.getAddress());
        }
        
    }

 
    public List<PaginatedCarShowroomDTO> getAllShowrooms(Pageable pageable) {

        return carShowRoomRepo.findAll(pageable)
        .getContent()
        .stream()
        .filter(carShowRoom -> carShowRoom.getIsDeleted() != true)
        .map(carShowRoom -> new PaginatedCarShowroomDTO(
                carShowRoom.getName(),
                carShowRoom.getCommercialRegistrationNumber(),
                carShowRoom.getContactNumber()
        )).toList(); 
    }


    public CarShowRoomDTO getCarShowRoomById(Long id) {
        
        CarShowRoom carShowRoom = carShowRoomRepo.findById(id)
        .filter(showroom -> showroom.getIsDeleted() != true) 
        .orElseGet(() -> { return new CarShowRoom(); }); 

        return new CarShowRoomDTO(carShowRoom.getName(), carShowRoom.getCommercialRegistrationNumber(), carShowRoom.getManagerName(), carShowRoom.getContactNumber(), carShowRoom.getAddress());
    }
}
