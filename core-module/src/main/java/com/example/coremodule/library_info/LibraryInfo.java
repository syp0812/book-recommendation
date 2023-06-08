package com.example.coremodule.library_info;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String library_area;

    @Column(nullable = false)
    private String library_name;

    @Column(nullable = false)
    private String library_addr;

    @Column(nullable = false)
    private String library_url;

    @Builder
    public LibraryInfo(String library_area, String library_name, String library_addr, String library_url) {
        this.library_area = library_area;
        this.library_name = library_name;
        this.library_addr = library_addr;
        this.library_url = library_url;
    }
}
