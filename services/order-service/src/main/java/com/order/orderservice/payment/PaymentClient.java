package com.order.orderservice.payment;

import com.order.orderservice.payment.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}")
public interface PaymentClient {
    @PostMapping("/create")
    PaymentResponse requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}

