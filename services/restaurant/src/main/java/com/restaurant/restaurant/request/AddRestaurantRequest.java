package com.restaurant.restaurant.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddRestaurantRequest {
    private String name;
    private String address;
    private String phone;
    private UUID ownerId;
}
