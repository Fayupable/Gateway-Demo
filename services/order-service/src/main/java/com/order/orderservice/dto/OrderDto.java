package com.order.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderDto {
    private String reference;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private UUID userId;

}
