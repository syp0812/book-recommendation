package com.example.coremodule.user.service;

import com.example.coremodule.user.domain.User;
import com.example.coremodule.user.domain.UserRepository;
import com.example.coremodule.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void login(UserRequestDto.Login request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Not found username"));

        if(!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    @Transactional
    public void signup(UserRequestDto.Signup request) {
        String username = request.getUsername();

        userRepository.findByUsername(username).ifPresent(user -> {
            throw new IllegalArgumentException("Already existing username");
        });
        userRepository.save(request.toEntity());
    }
}
