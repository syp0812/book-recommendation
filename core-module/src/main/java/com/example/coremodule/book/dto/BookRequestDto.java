package com.example.coremodule.book.dto;

import com.example.coremodule.book.domain.Book;
import com.example.coremodule.common.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookRequestDto {

    private String title;
    private String publisher;
    private String price;
    private String page;
    private String library;
    private String isbn;
    private String image;
    private String author;
    private Status.Book status;

    @Builder
    public BookRequestDto(String title, String publisher, String price, String page,
                          String library, String isbn, String image, String author, Status.Book status) {
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

    public Book toEntity(Status.Book status) {
        return Book.builder()
                .title(title)
                .publisher(publisher)
                .price(price)
                .page(page)
                .library(library)
                .isbn(isbn)
                .image(image)
                .author(author)
                .status(status)
                .build();
    }
}
