package com.fayupable.notification.entity;

import com.fayupable.notification.Enum.NotificationType;

import com.fayupable.notification.kafka.order.OrderConfirmation;
import com.fayupable.notification.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime createdAt;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
