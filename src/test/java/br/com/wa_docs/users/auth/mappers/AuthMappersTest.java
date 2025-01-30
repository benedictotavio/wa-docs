package br.com.wa_docs.users.auth.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;
import br.com.wa_docs.user.auth.mappers.AuthMappers;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.domains.UserRole;

@SpringBootTest()
class AuthMappersTest {

    private SignUpRequest signUpRequest;
    private User user;

    @BeforeEach
    void setUp() {
        signUpRequest = new SignUpRequest("John Doe", "john@doe.com", "123456", "123456", UserRole.USER);
    }

    @Test
    void shouldMapSignUpRequestToUser() {
        user = AuthMappers.toUser(signUpRequest);
        assertEquals(user.getUsername(), signUpRequest.username());
        assertEquals(user.getEmail(), signUpRequest.email());
        assertEquals(user.getPassword(), signUpRequest.password());
    }

}
