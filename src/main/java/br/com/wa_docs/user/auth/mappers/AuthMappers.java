package br.com.wa_docs.user.auth.mappers;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.domains.UserRole;
import br.com.wa_docs.user.enums.RoleName;

public class AuthMappers {
    private AuthMappers() {
    }

    public static User toUser(SignUpRequest signUpRequest) {
        return new User(
                signUpRequest.name(),
                signUpRequest.email(),
                signUpRequest.password(),
                new UserRole(RoleName.valueOf(signUpRequest.role())));
    }
}
