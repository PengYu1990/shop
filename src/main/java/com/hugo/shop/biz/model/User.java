package com.hugo.shop.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "First name should not be empty")
    private String firstName;

    @NotEmpty(message = "Second name should not be empty")
    private String lastName;

    private LocalDate dob;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    // 0 : Adminstrator
    // 1 : Common User
    private int type = 1;

}
