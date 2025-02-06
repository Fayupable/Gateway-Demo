package com.restaurant.restaurant.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantDto {
    private String name;
    private String address;
    private String phoneNumber;
    private UUID ownerId;
}
