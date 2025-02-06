package com.order.orderservice.controller;

import com.order.orderservice.request.AddOrderRequest;
import com.order.orderservice.response.OrderResponse;
import com.order.orderservice.service.IOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid AddOrderRequest addOrderRequest, HttpServletRequest request) {
        return ResponseEntity.ok(new OrderResponse("Order created successfully", orderService.createOrder(addOrderRequest, request)));
    }

    @GetMapping("/all")
    public ResponseEntity<OrderResponse> getAllOrders() {
        return ResponseEntity.ok(new OrderResponse("All orders fetched successfully", orderService.getAllOrders()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(new OrderResponse("Order fetched successfully", orderService.getOrderById(orderId)));
    }
}
