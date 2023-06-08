package com.example.coremodule.user.domain;

import com.example.coremodule.common.CommonConstant;
import com.example.coremodule.common.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = CommonConstant.RegExp.EMAIL)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String authority;

    @Enumerated(EnumType.STRING)
    private Status.User status;

    private String area;

    @Builder
    public User(String username, String email, String password, String authority, Status.User status, String area) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.status = status;
        this.area = area;
    }
}
