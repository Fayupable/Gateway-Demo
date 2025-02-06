package com.fayupable.notification.kafka;

import com.fayupable.notification.Enum.NotificationType;
import com.fayupable.notification.email.EmailService;
import com.fayupable.notification.email.IEmailService;
import com.fayupable.notification.entity.Notification;
import com.fayupable.notification.kafka.order.OrderConfirmation;
import com.fayupable.notification.kafka.payment.PaymentConfirmation;
import com.fayupable.notification.repository.INotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {
    private final INotificationRepository repository;
    private final IEmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .createdAt(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        var customerName = paymentConfirmation.userFirstname() + " " + paymentConfirmation.userLastname();
        emailService.sentPaymentSuccessEmail(
                paymentConfirmation.userEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumePaymentSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .createdAt(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var userName = orderConfirmation.user().firstname() + " " + orderConfirmation.user().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.user().email(),
                userName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()

        );

    }

}
