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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    private String date_publication;
    private String description;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    @ToString.Exclude
    private byte[] image;

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
    private int number_page;
    private int number_sold;
    private int number_in_stock; //number of books in stock
    private Date created_at;
    private Date updated_at;
}
