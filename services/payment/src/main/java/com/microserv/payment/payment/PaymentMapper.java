package com.microserv.payment.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {

        Payment payment = new Payment();
        payment.setId(request.id());
        payment.setAmount(request.amount());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setOrderId(request.orderId());

        return payment;
    }

}
