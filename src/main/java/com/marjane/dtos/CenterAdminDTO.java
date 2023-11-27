package com.marjane.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterAdminDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long centerId;
    private Long generalAdminId;

 }

