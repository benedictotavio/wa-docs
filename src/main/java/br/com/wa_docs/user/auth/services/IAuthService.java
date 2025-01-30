package br.com.wa_docs.user.auth.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;

public interface IAuthService extends UserDetailsService {
    void signUp(SignUpRequest signUpRequest);
}
