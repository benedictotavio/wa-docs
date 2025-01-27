package br.com.wa_docs.user.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;
import br.com.wa_docs.user.auth.services.IAuthService;
import jakarta.validation.Valid;

@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest requestDto) {
        this.authService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
