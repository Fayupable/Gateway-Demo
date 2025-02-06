package com.order.orderservice.service;

import com.order.orderservice.dto.OrderLineDto;
import com.order.orderservice.mapper.OrderLineMapper;
import com.order.orderservice.repository.IOrderLineRepository;
import com.order.orderservice.request.AddOrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService implements IOrderLineService {
    private final IOrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;


    @Override
    public UUID saveOrderLine(AddOrderLineRequest addOrderLineRequest) {
        var orderLine = this.orderLineMapper.toOrderLine(addOrderLineRequest);
        orderLine = this.orderLineRepository.save(orderLine);
        return orderLine.getId();
    }

    @Override
    public List<OrderLineDto> findAllByOrderId(UUID orderId) {
        return this.orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(this.orderLineMapper::toOrderLineDto)
                .collect(Collectors.toList());
    }
}
