package com.example.coremodule.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
}
