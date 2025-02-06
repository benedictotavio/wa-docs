package br.com.wa_docs.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateProjectDto(
        @NotEmpty(message = "Name is required") @NotBlank(message = "Name is required") @NotNull(message = "Name is required") String name,

        @NotEmpty(message = "Description is required") @NotBlank(message = "Description is required") @NotNull(message = "Description is required") String description,

        @NotNull(message = "Team is required") @NotEmpty(message = "Team is required") @NotBlank(message = "Team is required") Long teamId,

        @NotNull(message = "Owner is required") @NotEmpty(message = "Owner is required") @NotBlank(message = "Owner is required") Long ownerId) {
}
