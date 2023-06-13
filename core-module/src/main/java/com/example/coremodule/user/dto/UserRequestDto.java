package com.example.coremodule.user.dto;

import com.example.coremodule.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRequestDto {

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Login {

        @NotNull
        String username;

        @NotNull
        String password;
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Signup {

        @NotNull
        String username;

        @NotNull
        String password;

        @NotNull
        String area;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .area(area)
                    .build();
        }
    }
}
