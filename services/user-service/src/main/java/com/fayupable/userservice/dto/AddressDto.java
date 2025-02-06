package com.fayupable.userservice.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String houseNumber;
    private String zipCode;
}
