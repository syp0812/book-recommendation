package com.example.coremodule.book.domain;

import com.example.coremodule.common.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@DynamicUpdate
@Where(clause = "status = 'ALIVE'")
@SQLDelete(sql = "UPDATE post SET status = 'DELETE' WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String page;

    @Column(nullable = false)
    private String library;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status.Book status;

    @Builder
    public Book(String title, String publisher, String price, String page, String library,
                String isbn, String image, String author, Status.Book status) {
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.page = page;
        this.library = library;
        this.isbn = isbn;
        this.image = image;
        this.author = author;
        this.status = status;
    }
}
