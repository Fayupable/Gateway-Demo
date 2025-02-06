package com.order.orderservice.request;

import com.order.orderservice.Enum.PaymentMethod;
import com.order.orderservice.restaurant.request.AddPurchaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class AddOrderRequest {
    private String reference;
    private BigDecimal amount;
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
    private UUID userId;
    List<AddPurchaseRequest> products;

}
