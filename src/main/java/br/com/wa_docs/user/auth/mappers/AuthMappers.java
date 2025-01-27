package br.com.wa_docs.user.auth.mappers;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.domains.UserRole;

public class AuthMappers {
    private AuthMappers() { }


    public static SignUpRequest toSignUpRequest(User user) {
        return new SignUpRequest(
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getPassword(),
            user.getRole().name()
        );
    }

    public static User toUser(SignUpRequest signUpRequest) {
        return new User(
            signUpRequest.name(),
            signUpRequest.email(),
            signUpRequest.password(),
            UserRole.valueOf(signUpRequest.role())
        );
    }
}
