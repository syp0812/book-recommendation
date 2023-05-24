package com.example.coremodule.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate          // 변경된 컬럼만 update
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String image;

    @Builder
    public Post(String title, String content, String tag, String image) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.image = image;
    }
}
