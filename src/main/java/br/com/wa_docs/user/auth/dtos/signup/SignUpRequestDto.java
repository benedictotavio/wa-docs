package br.com.wa_docs.user.auth.dtos.signup;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
                @NotBlank(message = "Name cannot be blank") @NotNull(message = "Name cannot be null") @NotEmpty(message = "Name cannot be empty") String username,

                @Email(message = "Invalid email format") @NotBlank(message = "Email cannot be blank") @NotNull(message = "Email cannot be null") @NotEmpty(message = "Email cannot be empty") String email,

                @NotBlank(message = "Password cannot be blank") @NotNull(message = "Password cannot be null") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters") @NotEmpty(message = "Password cannot be empty") String password,

                @NotBlank(message = "Confirm password cannot be blank") @NotNull(message = "Confirm password cannot be null") @NotEmpty(message = "Confirm password cannot be empty") String confirmPassword,

                @NotBlank(message = "Role cannot be blank") @NotNull(message = "Role cannot be null") @Size(min = 1, message = "At least one role must be selected") @Size(max = 3, message = "Maximum of 3 roles can be selected") Set<String> roles) {
}
