package com.microserv.order.kafka;

import com.microserv.order.customer.CustomerResponse;
import com.microserv.order.order.PaymentMethod;
import com.microserv.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
