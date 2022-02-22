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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long customer_id;
    private Long store_id;
    private String first_name;
    private String last_name;
    private String email;
    private Long address_id;
    private boolean activebool;
    private Date create_date;
    private Date last_update;
    private int active;


}
