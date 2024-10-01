package com.myproject.library.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

    String email;
    String address;
    String phone;

//    @DobConstraint(min = 15, message = "INVALID_DOB")
    LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "roleID", nullable = false)
    Role role;

}
