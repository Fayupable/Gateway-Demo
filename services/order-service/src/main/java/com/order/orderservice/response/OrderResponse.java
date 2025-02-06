package com.order.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String message;
    private Object data;
}
