package com.microserv.order.orderline;

import com.microserv.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {

        var order = new Order();
        order.setId(request.orderId());

        OrderLine orderLine = new OrderLine();
        orderLine.setId(request.id());
        orderLine.setOrder(order);
        orderLine.setProductId(request.productId());
        orderLine.setQuantity(request.quantity());

        return orderLine;
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        OrderLineResponse orderLineResponse = new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );

        return orderLineResponse;
    }

}
