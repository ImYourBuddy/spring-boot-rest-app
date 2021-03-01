package com.imyourbuddy.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @NotNull(message = "Please provide a price")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @NotEmpty(message = "Please provide a warehouse")
    private String warehouse;

    @NotNull(message = "Please provide a quantity")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    private boolean deleted;
}
