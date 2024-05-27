package br.edu.ifpb.easyschoolback.presentation.controller;

import br.edu.ifpb.easyschoolback.presentation.controller.contract.AuthApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.auth.LoginRequestDto;
import br.edu.ifpb.easyschoolback.security.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestScope
@Slf4j
@RequiredArgsConstructor
public class AuthController implements AuthApiContract {

    private final LoginService loginService;

    @Override
    public ResponseEntity<?> login(final @Valid @RequestBody LoginRequestDto request) {
        log.info("Login request for user: {}", request.getUsername());
        final var token = loginService.login(request);
        return ok().header(SET_COOKIE, token.toString()).body(null);
    }

    @Override
    public ResponseEntity<?> logout() {
        return ok().header(SET_COOKIE, loginService.logout().toString()).body(null);
    }
}