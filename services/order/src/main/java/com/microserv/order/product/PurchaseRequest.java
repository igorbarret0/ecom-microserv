package com.microserv.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Long productId,

        @Positive(message = "Quantity is required")
        double quantity

) {
}
