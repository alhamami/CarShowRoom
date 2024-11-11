package com.elm.carshowrooms.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UpdateCarShowRoomDTO {

    @JsonProperty("contact_number")
    private int contactNumber;

    @JsonProperty("manager_name")
    private String managerName;

    private String address;



    public UpdateCarShowRoomDTO(int contactNumber, String managerName,  String address){
       
        this.managerName = managerName;
        this.contactNumber = contactNumber;
        this.address = address;
      
    }

    public UpdateCarShowRoomDTO() {
    }

    public String getManagerName() {
        return managerName;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

}