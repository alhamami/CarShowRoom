package com.elm.carshowrooms.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elm.carshowrooms.DTO.CarDTO;
import com.elm.carshowrooms.model.Car;
import com.elm.carshowrooms.model.CarShowRoom;
import com.elm.carshowrooms.repository.CarRepo;
import com.elm.carshowrooms.repository.CarShowRoomRepo;

@Service
public class CarService {

    @Autowired
    private CarShowRoomRepo carShowRoomRepo;

    @Autowired
    private CarRepo carRepo;


    public CarDTO saveCar(Long carShowRoomId, CarDTO newCar) {

        CarDTO carDTO = new CarDTO();;
        
        Optional<CarShowRoom> checkCarShowRoom = carShowRoomRepo.findById(carShowRoomId);

         if (checkCarShowRoom.isPresent()) {
           
            Car checkCar = checkCarShowRoom.get().getCars().stream().filter(car -> car.getVin().equalsIgnoreCase(newCar.getVin())).findFirst().orElse(null); 

            if (checkCar == null) {
                Car car = new Car();

                car.setId(newCar.getId());
                car.setVin(newCar.getVin());
                car.setMaker(newCar.getMaker());
                car.setModel(newCar.getModel());
                car.setModelYear(newCar.getModelYear());
                car.setPrice(newCar.getPrice());
                car.setCarShowRoom(checkCarShowRoom.get());
        
                Car savedCar = carRepo.save(car);
                carDTO = new CarDTO(savedCar.getId(), savedCar.getVin(), savedCar.getMaker(), savedCar.getModel(), savedCar.getModelYear(), savedCar.getPrice());
                return carDTO;
               
            }
     
             carDTO.setVin("exist");

        } 
        return carDTO;

    }
    
}
