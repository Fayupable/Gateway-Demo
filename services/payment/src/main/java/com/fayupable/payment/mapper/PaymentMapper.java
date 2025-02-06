package com.fayupable.payment.mapper;

import com.fayupable.payment.entity.Payment;
import com.fayupable.payment.request.AddPaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

  public Payment toPayment(AddPaymentRequest request) {
    if (request == null) {
      return null;
    }
    return Payment.builder()
        .id(request.id())
        .paymentMethod(request.paymentMethod())
        .amount(request.amount())
        .orderId(request.orderId())
        .build();
  }
}
