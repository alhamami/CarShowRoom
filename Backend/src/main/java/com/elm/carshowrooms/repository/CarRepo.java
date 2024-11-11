package com.elm.carshowrooms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.elm.carshowrooms.model.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c JOIN c.carShowRoom s WHERE (:vin IS NULL OR c.vin = :vin) AND (:maker IS NULL OR c.maker = :maker) AND " +
    "(:model IS NULL OR c.model = :model) AND (:modelYear IS NULL OR c.modelYear = :modelYear) AND (:price IS NULL OR c.price = :price) AND (:carShowroomID IS NULL OR s.id = :carShowroomID)")
    Page<Car> findCarsByAllParams(@Param("vin") String vin, @Param("maker") String maker,  @Param("model") String model, @Param("modelYear") Integer model_year, @Param("price") Double price, @Param("carShowroomID") Long carShowroomID, Pageable pageable);

}
