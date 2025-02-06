package com.fayupable.notification.kafka.order;

import java.util.UUID;

public record User(
        UUID id,
        String firstname,
        String lastname,
        String email
) {
}
