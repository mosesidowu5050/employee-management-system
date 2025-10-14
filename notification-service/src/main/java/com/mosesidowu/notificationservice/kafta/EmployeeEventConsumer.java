package com.mosesidowu.notificationservice.kafta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeEventConsumer {

    @KafkaListener(topics = "employee-events", groupId = "notification-group")
    public void onEmployeeEvent(String message) {
        log.info("📩 Received event on topic employee-events: {}", message);
        // TODO: call email/SMS providers here (async) or persist notification
        log.info("✅ Simulated notification action for event: {}", message);
    }
}
