package com.microserv.order.payment;

import com.microserv.order.customer.CustomerResponse;
import com.microserv.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
