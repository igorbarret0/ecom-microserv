package com.microserv.notification.notification;

import com.microserv.notification.email.EmailService;
import com.microserv.notification.kafka.order.OrderConfirmation;
import com.microserv.notification.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {

    private NotificationRepository notificationRepository;
    private EmailService emailService;

    public NotificationConsumer (NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void consumerPaymentConfirmationNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {

        var notification = new Notification();
        notification.setNotificationType(NotificationType.PAYMENT_CONFIRMATION);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setPaymentConfirmation(paymentConfirmation);

        notificationRepository.save(notification);

        // send email
        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sentPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumerOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {

        var notification = new Notification();
        notification.setNotificationType(NotificationType.ORDER_CONFIRMATION);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setOrderConfirmation(orderConfirmation);

        notificationRepository.save(notification);

        // send email
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sentOrderSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }



}
