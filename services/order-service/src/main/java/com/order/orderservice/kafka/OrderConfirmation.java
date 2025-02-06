package com.order.orderservice.kafka;

import com.order.orderservice.Enum.PaymentMethod;
import com.order.orderservice.restaurant.dto.PurchaseDto;
import com.order.orderservice.user.dto.UserDto;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        UserDto user,
        List<PurchaseDto> purchases
) {
}
