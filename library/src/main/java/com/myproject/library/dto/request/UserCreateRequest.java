package com.myproject.library.dto.request;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 8, message = "USER_INVALID")
    private String username;
    private String password;

    @Column(name = "decryptedpasword")
    private String decryptedpassword;

    private String email;
    private String address;
    private String phone;

    private LocalDate Dob;

}
