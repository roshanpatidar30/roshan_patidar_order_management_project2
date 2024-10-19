package com.order_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    @KafkaListener(topics = "order_status", groupId = "order_group")
    public void consumeOrderStatus(String message) {
        System.out.println("Order status update: " + message);
    }
}
