package com.example.dvdrental.api;

import com.example.dvdrental.entity.Customer;
import com.example.dvdrental.repository.CustomerRepository;
import com.example.dvdrental.service.kafka.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageSenderContoroller {

    private final MessagePublisher messagePublisher;
    private final CustomerRepository customerRepository;

    public MessageSenderContoroller(MessagePublisher messagePublisher, CustomerRepository customerRepository) {
        this.messagePublisher = messagePublisher;
        this.customerRepository = customerRepository;
    }


    @GetMapping("/{topicName}/{message}")
    public ResponseEntity sendMessage(@PathVariable String topicName, @PathVariable String message) throws URISyntaxException {
        messagePublisher.sendMessage(topicName, message);
        customerRepository.findAll().stream().forEach(customer -> messagePublisher.sendCustomer("customer-topic", customer));
        return ResponseEntity.ok().build();
    }

}
