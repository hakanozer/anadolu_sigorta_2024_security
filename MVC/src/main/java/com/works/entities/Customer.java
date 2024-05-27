package com.works.entities;

import com.works.validators.CityValid;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 100)
    @Size(min = 2, max = 100)
    @NotEmpty
    @NotNull
    private String name;

    @Size(min = 2, max = 100)
    @NotEmpty
    @Column(length = 100)
    @NotNull
    private String surname;

    @Column(length = 200)
    @Size(min = 6, max = 200)
    @NotEmpty
    @Email
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @CityValid
    private String city;

    @NotEmpty
    @NotNull
    private String password;

}
