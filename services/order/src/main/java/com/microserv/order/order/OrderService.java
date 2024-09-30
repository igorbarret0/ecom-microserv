package com.microserv.order.order;

import com.microserv.order.customer.CustomerClient;
import com.microserv.order.exception.BusinessException;
import com.microserv.order.kafka.OrderConfirmation;
import com.microserv.order.kafka.OrderProducer;
import com.microserv.order.orderline.OrderLineRequest;
import com.microserv.order.orderline.OrderLineService;
import com.microserv.order.payment.PaymentClient;
import com.microserv.order.payment.PaymentRequest;
import com.microserv.order.product.ProductClient;
import com.microserv.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerClient customerClient;
    private ProductClient productClient;
    private OrderMapper orderMapper;
    private OrderLineService orderLineService;
    private OrderProducer orderProducer;
    private PaymentClient paymentClient;

    public OrderService (OrderRepository orderRepository, CustomerClient customerClient,
                         ProductClient productClient, OrderMapper orderMapper, OrderLineService orderLineService,
                         OrderProducer orderProducer, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
        this.paymentClient = paymentClient;
    }

    public Long createOrder(OrderRequest orderRequest) {

        // check the customer -> Open feign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order: No customer found with the providade id: " + orderRequest.customerId()));

        // purchase the product -> product microservice
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

        // persist order
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        // Ensure the order is saved and flushed to the database
        this.orderRepository.flush();  // Force the order to be persisted

        // persist order lines
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {

            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(), // Use the generated ID of the saved order
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),  // Use the order ID that was persisted
                orderRequest.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation to our notification microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Long id) {

        return  orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the provided id: " + id));
    }

}
