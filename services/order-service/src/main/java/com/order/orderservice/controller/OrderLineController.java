package com.order.orderservice.controller;

import com.order.orderservice.response.OrderResponse;
import com.order.orderservice.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order/order-line")
@RequiredArgsConstructor
public class OrderLineController {
    private final IOrderLineService orderLineService;

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findByOrderId(@PathVariable("order-id") UUID orderId) {
        return ResponseEntity.ok(new OrderResponse("Order lines fetched successfully", orderLineService.findAllByOrderId(orderId)));
    }
}
