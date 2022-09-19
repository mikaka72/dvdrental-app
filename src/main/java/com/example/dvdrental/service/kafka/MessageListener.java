package com.example.dvdrental.service.kafka;

import com.example.dvdrental.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @KafkaListener(topics = "ping-topic", groupId = "ping-group")
    public void listenPing(String message) {
       log.info("Received Message [{}] to topic group ping-group",  message );
    }

    @KafkaListener(topics = "customer-topic", groupId = "customer-group", containerFactory = "customerKafkaListenerContainerFactory")
    public void listenCustomer(Customer customer) {
        log.info("Received Message [{}] to topic customer-topic",  customer.toString() );
    }

}
