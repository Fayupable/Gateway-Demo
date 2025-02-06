package com.order.orderservice.mapper;

import com.order.orderservice.dto.OrderDto;
import com.order.orderservice.entity.Order;
import com.order.orderservice.request.AddOrderRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(AddOrderRequest request) {
        return Order.builder()
                .reference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .userId(request.getUserId())
                .build();
    }

    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        orderDto.setReference(order.getReference());
        orderDto.setPaymentMethod(order.getPaymentMethod().name());
        orderDto.setTotalAmount(order.getTotalAmount());
        return orderDto;
    }
}
