package com.elm.carshowrooms.DTO;

import com.elm.carshowrooms.model.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CarDTO {

    private Long id;

    @Size(max = 25)
    @NotBlank(message = "vin can't be empty")
    private String vin;

    @Size(max = 25)
    @NotBlank(message = "maker can't be empty")
    private String maker;

    @Size(max = 25)
    @NotBlank(message = "model can't be empty")
    private String model;

    @Max(value = 4)
    @NotBlank(message = "model_year can't be empty")
    @JsonProperty("model_year")
    private int modelYear; 

    @NotBlank(message = "price can't be empty")
    private double price;

    private String carShowRoom;


    
    
    public void setCarShowRoom(String carShowRoom) {
        this.carShowRoom = carShowRoom;
    }

    public String getCarShowRoom() {
        return carShowRoom;
    }

    public CarDTO(Long id, String vin, String maker, String model, int modelYear, double price) {
        this.id = id;
        this.vin = vin;
        this.maker = maker;
        this.model = model;
        this.modelYear = modelYear;
        this.price = price;
    }

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.vin = car.getVin();
        this.maker = car.getMaker();
        this.model = car.getModel();
        this.modelYear = car.getModelYear();
        this.price = car.getPrice();
        this.carShowRoom = car.getCarShowRoom().getName();
    }

    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setMaker(String maker) {
        this.maker = maker;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }
    public void setPrice(double price) {
        this.price = price;
    }


}
