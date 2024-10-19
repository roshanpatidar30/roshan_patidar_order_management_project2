package com.product_service.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "product-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendProductMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
