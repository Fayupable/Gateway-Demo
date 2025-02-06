package com.fayupable.notification.kafka.order;

import com.fayupable.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        User user,
        List<Product> products
) {
}
