package com.fayupable.payment.controller;

import com.fayupable.payment.request.AddPaymentRequest;
import com.fayupable.payment.response.PaymentResponse;
import com.fayupable.payment.service.IPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService service;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayments(@RequestBody @Valid AddPaymentRequest request) {
        return ResponseEntity.ok(new PaymentResponse("Payment created successfully", service.createPayment(request)));
    }
}
