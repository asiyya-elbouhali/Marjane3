package com.marjane.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "general_admins")
public class GeneralAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "generalAdmin", cascade = CascadeType.ALL)
    private List<CenterAdmin> centerAdmins = new ArrayList<>();


 }
