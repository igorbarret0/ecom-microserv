package com.microserv.payment.payment;

import com.microserv.payment.notification.NotificationProducer;
import com.microserv.payment.notification.PaymentNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private NotificationProducer notificationProducer;

    public PaymentService (PaymentRepository paymentRepository, PaymentMapper paymentMapper,
                           NotificationProducer notificationProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.notificationProducer = notificationProducer;
    }

    public Long createPayment(PaymentRequest request) {

        var payment = paymentRepository.save(paymentMapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }

}
