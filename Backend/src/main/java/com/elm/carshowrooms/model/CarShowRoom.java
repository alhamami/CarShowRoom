package com.elm.carshowrooms.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Entity
@Data

public class CarShowRoom {
    
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Id
    @Min(value = 10)
    @Max(value = 10)
    @Column(nullable = false, length = 10, unique = true)
    private Long commercialRegistrationNumber; 

    @Size(max = 100)
    @Column(length = 100)
    private String managerName;

    @Max(value = 15)
    @Column(nullable = false, length = 15)
    private int contactNumber;

    @Column(length = 255)
    private String address;

    @Size(max = 255)
    @Column(length = 5)
    private boolean isDeleted;



    @OneToMany(mappedBy = "carShowRoom", cascade = CascadeType.ALL)
    private List<Car> cars;




    

    public CarShowRoom() {
    }

    public CarShowRoom(String name, Long commercialRegistrationNumber, String managerName, int contactNumber,
            String address, List<Car> cars) {

        this.name = name;
        this.commercialRegistrationNumber = commercialRegistrationNumber;
        this.managerName = managerName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isDeleted = false;
        this.cars = cars;
    }


    public String getName() {
        return name;
    }

    public Long getCommercialRegistrationNumber() {
        return commercialRegistrationNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommercialRegistrationNumber(Long commercialRegistrationNumber) {
        this.commercialRegistrationNumber = commercialRegistrationNumber;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    } 

}
