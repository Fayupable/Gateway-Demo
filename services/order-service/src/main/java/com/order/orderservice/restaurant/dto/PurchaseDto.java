package com.order.orderservice.restaurant.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseDto {
    private UUID productId;
    private String name;
    private String description;
    private double price;
    private double quantity;
}
