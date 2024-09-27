package com.microserv.order.order;

import java.math.BigDecimal;

public record OrderResponse(

        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId

) {
}
