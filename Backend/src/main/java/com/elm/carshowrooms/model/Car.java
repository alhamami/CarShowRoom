package com.elm.carshowrooms.model;


import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    @Column(nullable = false, length = 25)
    private String vin;

    @Size(max = 25)
    @Column(nullable = false, length = 25)
    private String maker;

    @Size(max = 25)
    @Column(nullable = false, length = 25)
    private String model;

    @Max(value = 4)
    @Column(nullable = false)
    private int modelYear; 

    @Column(nullable = false)
    private double price; 

    @ManyToOne
    @JoinColumn(name = "carShowroomID")
    private CarShowRoom carShowRoom;



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


}
