package com.imyourbuddy.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "buyer")
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Please provide a last name")
    @Column(name = "lastname")
    private String lastName;

    @NotEmpty(message = "Please provide a district")
    private String geography;

    @NotNull(message = "Please provide discount")
    @Min(value = 0, message = "Discount must be greater than or equal to 0")
    private Double discount;

    private boolean deleted;
}
