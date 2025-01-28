package br.com.wa_docs.users.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.domains.UserRole;
import br.com.wa_docs.user.enums.RoleName;

@SpringBootTest
class UserTest {

    private User user;
    private static final LocalDate createdDate = LocalDate.of(2023, 12, 29);
    private static final LocalDate updatedDate = LocalDate.of(2024, 12, 29);

    @BeforeEach
    void setUp() {
        user = new User(
                "username",
                "email",
                "password",
                new UserRole(RoleName.USER));
    }

    @Test
    void testUser() {
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(createdDate, user.getCreatedAt());
        assertEquals(updatedDate, user.getUpdatedAt());
    }
}
