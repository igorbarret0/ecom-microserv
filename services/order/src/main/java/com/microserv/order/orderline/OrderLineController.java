package com.microserv.order.orderline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }


    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable(name = "order-id") Long orderId) {

        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }

}
