package com.microserv.order.orderline;


public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity
) {
}
