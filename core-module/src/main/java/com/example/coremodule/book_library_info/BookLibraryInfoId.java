package com.example.coremodule.book_library_info;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class BookLibraryInfoId implements Serializable {

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "library_info_id")
    private Long libraryInfoId;

    public BookLibraryInfoId(Long bookId, Long libraryInfoId) {
        this.bookId = bookId;
        this.libraryInfoId = libraryInfoId;
    }
}
