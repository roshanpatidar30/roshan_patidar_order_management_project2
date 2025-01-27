package com.customer_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "customer-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCustomerMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}