package com.mosesidowu.employee_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeEventProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${employee.topic.name}")
    private String topic;

    public void publishEmployeeCreated(String message, String key) {
        log.info("Publishing employee created message to {} : {}", topic, message);
        kafkaTemplate.send(topic, key, message);
    }
}
