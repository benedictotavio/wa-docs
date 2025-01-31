package br.com.wa_docs.users.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.wa_docs.user.domains.User;

@SpringBootTest
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                "username",
                "email",
                "password");
    }

    @Test
    void testUser() {
        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertInstanceOf(LocalDate.class, user.getCreatedAt());
        assertInstanceOf(LocalDate.class, user.getUpdatedAt());
    }
}
