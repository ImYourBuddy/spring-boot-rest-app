package com.imyourbuddy.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Min(value = 1, message = "Shop id must be greater than or equal to 1")
    private int seller;

    @Min(value = 1, message = "Buyer id must be greater than or equal to 1")
    private int buyer;

    @Min(value = 1, message = "Book id must be greater than or equal to 1")
    private int book;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private int quantity;

    @Column(name = "total_price")
    @Min(value = 0, message = "Total price must be greater than or equal to 0")
    private Double totalPrice;
}
