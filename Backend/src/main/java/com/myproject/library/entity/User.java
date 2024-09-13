package com.myproject.library.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String username;
    String password;

    @Column(name = "decryptedpasword")
    String decryptedpassword;

    String email;
    String address;
    String phone;

    LocalDate dob;



}
