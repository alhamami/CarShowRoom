package com.elm.carshowrooms.DTO;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;


@Getter
public class CarShowRoomDTO {

    @Size(max = 100)
    @NotBlank(message = "name can't be empty")
    private String name;

    @JsonProperty("commercial_registration_number")
    @NotBlank(message = "commercial_Registration_Number can't be empty")
    @Min(value = 10)
    @Max(value = 10)
    private Long commercialRegistrationNumber;

    @Size(max = 100)
    @JsonProperty("manager_name")
    private String managerName;

    @JsonProperty("contact_number")
    @NotBlank(message = "contact_number can't be empty")
    @Max(value = 15)
    private int contactNumber;

    @Size(max = 255)
    private String address;




    public CarShowRoomDTO(String name, Long commercialRegistrationNumber, String managerName, int contactNumber, String address){
        this.name = name;
        this.commercialRegistrationNumber = commercialRegistrationNumber;
        this.managerName = managerName;
        this.contactNumber = contactNumber;
        this.address = address;
    }


    public CarShowRoomDTO() {
        
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

}