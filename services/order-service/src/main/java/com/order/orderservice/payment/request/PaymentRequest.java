package com.order.orderservice.payment.request;

import com.order.orderservice.Enum.PaymentMethod;
import com.order.orderservice.user.dto.UserDto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    UUID orderId,
    String orderReference,
    UserDto user
) {
}
