
package com.elm.carshowrooms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elm.carshowrooms.model.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

}
