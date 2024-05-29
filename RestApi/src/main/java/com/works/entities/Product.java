package com.works.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Product extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Size(min = 2, max = 100)
    @NotEmpty
    @NotNull
    private String title;

    @Max(1000000)
    @Min(2)
    @NotNull
    private Integer price;

}
