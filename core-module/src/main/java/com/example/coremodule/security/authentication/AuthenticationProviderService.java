package com.example.coremodule.security.authentication;

import com.example.coremodule.security.authorization.UserDetailsImpl;
import com.example.coremodule.security.authorization.UserDetailsServiceImpl;
import com.example.coremodule.user.domain.EncryptionAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // UserDetailsService로 DB에서 사용자 세부 정보 검색
        UserDetailsImpl user = userDetailsService.loadUserByUsername(username);

        if(user.getUser().getAlgorithm() == EncryptionAlgorithm.BCRYPT) {
            return checkPassword(user, password, bCryptPasswordEncoder);
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private Authentication checkPassword(UserDetailsImpl user, String rawPassword, PasswordEncoder encoder) {
        if(encoder.matches(rawPassword, user.getPassword())) {
            return new
                    UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword(), user.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
