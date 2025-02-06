package com.restaurant.restaurant.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddProductRequest {
    private String name;
    private String description;
    private double price;
    private UUID restaurantId;
}
