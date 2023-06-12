package com.example.coremodule.user.domain;

import com.example.coremodule.common.CommonConstant;
import com.example.coremodule.common.Status;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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

    @Enumerated(EnumType.STRING)
    private Status.User status;

    private String area;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Authority> authorities;

    @Builder
    public User(String username, String email, String password, String authority, Status.User status,
                String area, List<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.area = area;
        this.authorities = authorities;
    }
}
