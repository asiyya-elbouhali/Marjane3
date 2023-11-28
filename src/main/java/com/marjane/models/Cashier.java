package com.marjane.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    // Other relevant attributes, getters, setters, constructors
}
