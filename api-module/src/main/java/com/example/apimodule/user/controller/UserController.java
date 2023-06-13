package com.example.apimodule.user.controller;

import com.example.coremodule.common.Response;
import com.example.coremodule.user.dto.UserRequestDto;
import com.example.coremodule.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<Void> login(@Valid @RequestBody UserRequestDto.Login request) {
        userService.login(request);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/signup")
    public Response<Void> signup(@Valid @RequestBody UserRequestDto.Signup request) {
        userService.signup(request);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }
}
