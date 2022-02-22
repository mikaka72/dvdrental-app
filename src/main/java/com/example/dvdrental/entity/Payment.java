package com.example.dvdrental.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private Long payment_id;
    private Long customer_id;
    private Long staff_id;
    private Long rental_id;
    private Double amount;
    private Date payment_date;

}
