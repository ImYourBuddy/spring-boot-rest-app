package com.imyourbuddy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "buyer")
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    private String geography;

    private Double discount;
}
