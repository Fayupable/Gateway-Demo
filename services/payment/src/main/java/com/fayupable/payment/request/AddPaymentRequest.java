package com.fayupable.payment.request;

import com.fayupable.payment.enumerated.PaymentMethod;
import com.fayupable.payment.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public record AddPaymentRequest(
        UUID id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        User user
) {
}
