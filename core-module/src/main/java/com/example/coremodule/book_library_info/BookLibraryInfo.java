package com.example.coremodule.book_library_info;

import com.example.coremodule.book.Book;
import com.example.coremodule.library_info.LibraryInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BookLibraryInfo implements Persistable<BookLibraryInfoId> {

    @EmbeddedId
    private BookLibraryInfoId id;

    @MapsId("bookId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @MapsId("libraryInfoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_info_id")
    private LibraryInfo libraryInfo;

    @CreatedDate
    private LocalDate createdAt;

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
