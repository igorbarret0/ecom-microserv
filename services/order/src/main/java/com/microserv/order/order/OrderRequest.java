package com.microserv.order.order;

import com.microserv.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Long id,

        String reference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerId,

        List<PurchaseRequest> products
) {
}
