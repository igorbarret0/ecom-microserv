package com.microserv.order.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {

        Order newOrder = new Order();
        newOrder.setCustomerId(request.customerId());
        newOrder.setReference(request.reference());
        newOrder.setTotalAmount(request.amount());
        newOrder.setPaymentMethod(request.paymentMethod());

        return newOrder;
    }

    public OrderResponse toOrderResponse(Order order) {

         OrderResponse orderResponse = new OrderResponse(
                order.getId(),
                 order.getReference(),
                 order.getTotalAmount(),
                 order.getPaymentMethod(),
                 order.getCustomerId()
         );

         return orderResponse;
    }

}
