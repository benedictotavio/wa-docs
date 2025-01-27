package br.com.wa_docs.user.auth.services;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;

public interface IAuthService {
    void signUp(SignUpRequest signUpRequest);
}
