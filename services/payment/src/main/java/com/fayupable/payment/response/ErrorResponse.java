package com.fayupable.payment.response;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
