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
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue
    private Long rental_id;
    private Date rental_date;
    private Long inventory_id;
    private Long Customer_id;
    private Date return_date;
    private Long staff_id;
    private Date last_updated;

}
