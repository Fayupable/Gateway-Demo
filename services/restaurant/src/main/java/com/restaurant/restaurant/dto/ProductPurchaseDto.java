package com.restaurant.restaurant.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductPurchaseDto {
    private UUID productId;
    private String name;
    private String description;
    private double price;
    private double quantity;
}

