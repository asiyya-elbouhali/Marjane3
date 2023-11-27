package com.marjane.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "center_admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Column(name = "center_id")
    private Long centerId;

    @ManyToOne
    @JoinColumn(name = "center_id", insertable = false, updatable = false)
    private Center center;

    @ManyToOne
    @JoinColumn(name = "general_admin_id")
    private GeneralAdmin generalAdmin;

 }
