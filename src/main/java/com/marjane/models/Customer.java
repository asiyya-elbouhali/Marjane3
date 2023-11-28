package com.marjane.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int loyaltyPoints;
    // Other relevant attributes, getters, setters, constructors
}
