package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT SUM(amount) FROM payment", nativeQuery = true)
    Double totalRentalAmount();

    @Query(value = "SELECT MIN(payment_date) FROM payment", nativeQuery = true)
    Date startDate();
}
