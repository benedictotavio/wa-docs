package br.com.wa_docs.user.auth.dtos.login;

import jakarta.validation.constraints.*;

public record LoginRequestDto(
                @NotBlank(message = "Email cannot be blank") @NotNull(message = "Email cannot be null") @NotEmpty(message = "Email cannot be empty") @Email(message = "Invalid email format") String email,

                @NotBlank(message = "Password cannot be blank") @NotNull(message = "Password cannot be null") @NotEmpty(message = "Password cannot be empty") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters") String password) {
}