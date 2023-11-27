package com.marjane.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralAdminDTO {

    private Long id;
    private String name;
    private String email;
    private String password;



    public GeneralAdminDTO(String name, String email, String password) {
         this.name = name;
        this.email = email;
        this.password = password;
    }

    public GeneralAdminDTO(Long id, String name, String email, String password) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

