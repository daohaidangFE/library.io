package com.myproject.library.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude  //reduce boilerplate code in Java
    private Collection<Book> bookList;

    private Date created_at;
    private Date updated_at;

}
