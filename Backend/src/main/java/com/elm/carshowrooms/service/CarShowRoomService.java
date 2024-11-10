package com.elm.carshowrooms.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elm.carshowrooms.DTO.CarShowRoomDTO;
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


}