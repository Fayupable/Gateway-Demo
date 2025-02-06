package com.fayupable.payment.service;

import com.fayupable.payment.request.AddPaymentRequest;

import java.util.UUID;

public interface IPaymentService {
    UUID createPayment(AddPaymentRequest paymentRequest);
}
