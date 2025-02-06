package com.order.orderservice.restaurant.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class AddPurchaseRequest {
    @NotNull(message = "Product id is required")
    private UUID productId;
    @Positive(message = "Quantity should be greater than 0")
    private double quantity;
}