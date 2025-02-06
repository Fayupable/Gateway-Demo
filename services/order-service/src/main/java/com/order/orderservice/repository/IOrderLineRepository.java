package com.order.orderservice.repository;

import com.order.orderservice.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderLineRepository extends JpaRepository<OrderLine, UUID> {
    List<OrderLine> findAllByOrderId(UUID orderId);
}
