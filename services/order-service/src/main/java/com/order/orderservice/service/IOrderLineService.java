package com.order.orderservice.service;

import com.order.orderservice.dto.OrderLineDto;
import com.order.orderservice.request.AddOrderLineRequest;

import java.util.List;
import java.util.UUID;

public interface IOrderLineService {
    UUID saveOrderLine(AddOrderLineRequest addOrderLineRequest);

    List<OrderLineDto> findAllByOrderId(UUID orderId);
}
