package com.order.orderservice.mapper;

import com.order.orderservice.dto.OrderDto;
import com.order.orderservice.dto.OrderLineDto;
import com.order.orderservice.entity.OrderLine;
import com.order.orderservice.request.AddOrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(AddOrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    public OrderLineDto toOrderLineDto(OrderLine orderLine) {
        OrderLineDto orderLineDto = new OrderLineDto();
        orderLineDto.setId(orderLine.getId());
        orderLineDto.setQuantity(orderLine.getQuantity());
        return orderLineDto;
    }
}
