package br.edu.ifpb.easyschoolback.security.service;

import br.edu.ifpb.easyschoolback.presentation.dtos.auth.LoginRequestDto;
import br.edu.ifpb.easyschoolback.security.jwt.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public ResponseCookie login(final LoginRequestDto request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final var cookie = tokenUtils.generateToken(userDetails);
        return cookie;
    }

    public ResponseCookie logout() {
        return tokenUtils.cleanToken();
    }


}
