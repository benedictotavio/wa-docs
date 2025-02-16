package br.com.wa_docs.user.auth.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.user.auth.dtos.login.LoginRequestDto;
import br.com.wa_docs.user.auth.dtos.login.LoginResponseDto;
import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.auth.dtos.signup.SignUpResponseDto;
import br.com.wa_docs.user.auth.services.IAuthService;
import br.com.wa_docs.user.domains.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final IAuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        try {
            return ResponseEntity.created(
                    URI.create("/api/v1/auth/signup")).body(
                            this.authService.signUp(requestDto));
        } catch (Exception e) {
            System.out.printf("Erro ao realizar signup: %s", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new SignUpResponseDto(
                            e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(
                    loginRequestDto.email(),
                    loginRequestDto.password());

            Authentication auth = this.authenticationManager.authenticate(login);
            User user = (User) auth.getPrincipal();
            String token = this.authService.login(user);
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (Exception e) {
            System.out.printf("Erro ao realizar login: %s", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
