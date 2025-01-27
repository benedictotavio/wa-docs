package br.com.wa_docs.users.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.domains.UserRole;

@SpringBootTest
class UserTest {

    private User user;
    private static final LocalDate createdDate = LocalDate.of(2023, 12, 29);
    private static final LocalDate updatedDate = LocalDate.of(2024, 12, 29);

    @BeforeEach
    void setUp() {
        user = new User(
                1L,
                "username",
                "email",
                "password",
                UserRole.USER,
                createdDate,
                updatedDate
        );
    }

    @Test
    void testUser() {
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(UserRole.USER, user.getRole());
        assertEquals(createdDate, user.getCreatedAt());
        assertEquals(updatedDate, user.getUpdatedAt());
    }

    @Test
    void testUserWithNullValues() {
        user = new User();
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getRole());
        assertEquals(null, user.getCreatedAt());
        assertEquals(null, user.getUpdatedAt());
    }
}
