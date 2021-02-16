package com.imyourbuddy.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private int orderNumber;

    private Date date;

    private int seller;

    private int buyer;

    private int book;

    private int quantity;

    @Column(name = "total_price")
    private Double totalPrice;
}
