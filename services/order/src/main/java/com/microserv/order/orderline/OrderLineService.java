package com.microserv.order.orderline;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    private OrderLineMapper orderLineMapper;

    public OrderLineService(OrderLineRepository orderLineRepository, OrderLineMapper orderLineMapper) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineMapper = orderLineMapper;
    }

    public Long saveOrderLine(OrderLineRequest request) {

        var orderLine = orderLineMapper.toOrderLine(request);
        return orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Long id) {

        return orderLineRepository.findAllByOrderId(id)
                .stream().map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
