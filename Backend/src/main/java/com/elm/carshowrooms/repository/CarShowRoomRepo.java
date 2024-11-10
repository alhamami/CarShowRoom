package com.elm.carshowrooms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.elm.carshowrooms.model.CarShowRoom;

@Repository
public interface CarShowRoomRepo extends JpaRepository<CarShowRoom, Long> {
 
}

