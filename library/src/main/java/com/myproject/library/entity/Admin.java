package com.myproject.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set the id attribute to increment from 0
    private int id;

    @OneToOne // represents a one to one relationship
    @JoinColumn(name = "account_id")
    private Account account;

    private String name;
    private String address;
    private String phone;
}
