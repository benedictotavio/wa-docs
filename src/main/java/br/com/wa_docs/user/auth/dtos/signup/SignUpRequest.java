package br.com.wa_docs.user.auth.dtos.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
    @NotBlank(message = "Name cannot be blank")
    String name,

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email,

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password,
    
    @NotBlank(message = "Confirm password cannot be blank")
    String confirmPassword,

    @NotBlank(message = "Role cannot be blank")
    String role
    ) {
}
