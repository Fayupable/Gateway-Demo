package com.fayupable.notification.email;

import com.fayupable.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.util.List;

public interface IEmailService {
    @Async
    void sentPaymentSuccessEmail(String destinationEmail,
                                 String userName,
                                 BigDecimal amount,
                                 String orderReference) throws MessagingException;

    @Async
    void sendOrderConfirmationEmail(String destinationEmail,
                                    String userName,
                                    BigDecimal amount,
                                    String orderReference,
                                    List<Product> products) throws MessagingException;
}
