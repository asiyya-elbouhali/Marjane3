package com.marjane.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
 public class CategoryDTO {

    private Long id;
    private String name;
    private String description;

 }
