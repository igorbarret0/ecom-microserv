package com.microserv.order.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
