package com.myproject.library.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToMany
    Set<Category> categorys;

    String title;
    String date_publication;
    String description;

    @Transient  //no saved to database
    MultipartFile fileData;

    @Transient
    boolean newBook;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    Collection<OrderDetail> orderDetailList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @ToString.Exclude
    Collection<Comment> commentList;

    float price;
    Long number_page;
    Long number_sold;
    Long number_in_stock; //number of books in stock
    Date created_at;
    Date updated_at;
}
