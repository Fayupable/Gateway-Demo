package com.order.orderservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderLineDto {
    private UUID id;
    double quantity;
}
