package com.marjane.models;

import  jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Cashier cashier;


}
