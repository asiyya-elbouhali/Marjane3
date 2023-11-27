package com.marjane.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "centers")
@Data
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    @Getter
    @OneToMany(mappedBy = "center")
    private Collection<CenterAdmin> centerAdmins;

    public void setCenterAdmins(Collection<CenterAdmin> centerAdmins) {
        this.centerAdmins = centerAdmins;
    }
}
