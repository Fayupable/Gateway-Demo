package com.order.orderservice.request;

import java.util.UUID;

public record AddOrderLineRequest(
        UUID id,
        UUID orderId,
        UUID productId,
        double quantity
) {
}
