package com.imyourbuddy.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "shop")
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Provide a name")
    private String name;

    @NotEmpty(message = "Provide a district")
    private String geography;

    @NotNull(message = "Provide a commission")
    @Min(value = 0, message = "Commission must be greater than or equal to 0")
    private Double commission;

    private boolean deleted;
}
