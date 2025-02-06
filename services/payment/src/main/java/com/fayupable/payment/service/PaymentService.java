package com.fayupable.payment.service;

import com.fayupable.payment.mapper.PaymentMapper;
import com.fayupable.payment.notification.NotificationProducer;
import com.fayupable.payment.notification.PaymentNotificationRequest;
import com.fayupable.payment.repositiory.IPaymentRepository;
import com.fayupable.payment.request.AddPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final IPaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public UUID createPayment(AddPaymentRequest paymentRequest) {
        var payment = this.repository.save(this.mapper.toPayment(paymentRequest));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.user().firstname(),
                        paymentRequest.user().lastname(),
                        paymentRequest.user().email()
                )
        );
        return payment.getId();
    }


}
