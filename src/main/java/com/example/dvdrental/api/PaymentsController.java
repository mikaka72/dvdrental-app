package com.example.dvdrental.api;

import com.example.dvdrental.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private PaymentRepository paymentRepository;

    public PaymentsController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/total")
    public ResponseEntity getPayments() {
        return ResponseEntity.ok(paymentRepository.totalRentalAmount());
    }

    @GetMapping("/start")
    public ResponseEntity getStartDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        return ResponseEntity.ok(response.put("start", format.format(paymentRepository.startDate())));
    }
}
