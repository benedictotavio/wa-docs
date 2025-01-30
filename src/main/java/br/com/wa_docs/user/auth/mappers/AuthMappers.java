package br.com.wa_docs.user.auth.mappers;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.domains.User;

public class AuthMappers {
    private AuthMappers() {
    }

    public static User toUser(SignUpRequestDto signUpRequest) {
        return new User(
                signUpRequest.username(),
                signUpRequest.email(),
                signUpRequest.password(),
                signUpRequest.roles());
    }
}
