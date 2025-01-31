package br.com.wa_docs.users.auth.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.auth.enums.ERole;
import br.com.wa_docs.user.auth.mappers.AuthMappers;
import br.com.wa_docs.user.domains.User;

@SpringBootTest()
class AuthMappersTest {

    private SignUpRequestDto signUpRequest;
    private User user;

    private Role[] roles = {
            new Role(ERole.USER),
            new Role(ERole.ADMIN)
    };

    @BeforeEach
    void setUp() {
        signUpRequest = new SignUpRequestDto(
                "John Doe", "john@doe.com", "123456", "123456", roles);
    }

    @Test
    void shouldMapSignUpRequestToUser() {
        user = AuthMappers.toUser(signUpRequest);
        assertEquals(user.getUsername(), signUpRequest.username());
        assertEquals(user.getEmail(), signUpRequest.email());
        assertEquals(user.getPassword(), signUpRequest.password());
        assert user.getAuthorities().size() == 2;
    }

}
