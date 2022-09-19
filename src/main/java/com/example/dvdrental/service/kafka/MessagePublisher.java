package com.example.dvdrental.service.kafka;

import com.example.dvdrental.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Service
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Customer> customerKafkaTemplate;

    public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Customer> customerKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    public void sendMessage(String topicName, String message) {
        log.info("Sending message {} to topic {}", message, topicName);
        kafkaTemplate.send(topicName, message).addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message: {} with offset: {}", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendCustomer(String topicName, Customer customer) {
        log.info("Sending customer id  {} data to topic {}", customer.getCustomer_id(), topicName);
        customerKafkaTemplate.send(topicName, customer).addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Customer> result) {
                log.info("Sent customer id: {} with offset: {}", customer.getCustomer_id(), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + customer.getCustomer_id() + "] due to : " + ex.getMessage());
            }
        });
    }

}
