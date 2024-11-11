package com.elm.carshowrooms.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PaginatedCarShowroomDTO {

    private String name;

    @JsonProperty("commercial_registration_number")
    private Long commercialRegistrationNumber;

    @JsonProperty("contact_number")
    private int contactNumber;

    public PaginatedCarShowroomDTO(String name, Long commercialRegistrationNumber, int contactNumber) {
        this.name = name;
        this.commercialRegistrationNumber = commercialRegistrationNumber;
        this.contactNumber = contactNumber;
    }
}