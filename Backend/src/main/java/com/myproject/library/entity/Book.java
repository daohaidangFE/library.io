package com.myproject.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    private String date_publication;
    private String description;

    @Transient  //no saved to database
    private MultipartFile fileData;

    @Transient
    private boolean newBook;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Collection<OrderDetail> orderDetailList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Comment> commentList;

    private float price;
    private Long number_page;
    private Long number_sold;
    private Long number_in_stock; //number of books in stock
    private Date created_at;
    private Date updated_at;
}
