package com.order.orderservice.service;

import com.order.orderservice.dto.OrderDto;
import com.order.orderservice.request.AddOrderRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    UUID createOrder(AddOrderRequest addOrderRequest, HttpServletRequest request);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(UUID orderId);
}
