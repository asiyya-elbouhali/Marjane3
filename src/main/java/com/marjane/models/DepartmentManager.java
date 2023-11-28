package com.marjane.models;

import jakarta.persistence.*;
 import lombok.Data;


@Entity
@Data
 public class DepartmentManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


 }
