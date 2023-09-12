package com.kt.navi.gw.period.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic")
    public void listen(String message) {
        // Kafka 메시지 처리 로직을 작성합니다.
        System.out.println("Received message: " + message);
    }
}
