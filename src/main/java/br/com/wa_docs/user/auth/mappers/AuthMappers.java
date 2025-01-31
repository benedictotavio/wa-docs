package br.com.wa_docs.user.auth.mappers;

import java.util.Set;

import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.auth.enums.ERole;
import br.com.wa_docs.user.domains.User;

public class AuthMappers {
    private AuthMappers() {
    }

    public static User toUser(SignUpRequestDto signUpRequest) {
        Set<Role> roles = Set.of();
        for (String role : signUpRequest.roles()) {
            roles = Set.of(new Role(ERole.valueOf(role)));
        }

        return new User(
                signUpRequest.username(),
                signUpRequest.email(),
                signUpRequest.password(),
                roles);
    }
}
