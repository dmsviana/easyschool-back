package br.edu.ifpb.easyschoolback.presentation.controller.contract;

import br.edu.ifpb.easyschoolback.presentation.dtos.auth.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication", description = "Operations related to authentication")
@RequestMapping("/auth")
public interface AuthApiContract {

    @Operation(
            summary = "Login",
            description = "Authenticate a user and return a token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request);

    @Operation(
            summary = "Logout",
            description = "Logout a user and invalidate the token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @PostMapping("/logout")
    ResponseEntity<?> logout();
}