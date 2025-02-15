package br.com.wa_docs.folder.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateFolderDto(
        @NotBlank(message = "Name is required")
        @NotEmpty(message = "Name is required")
        @NotNull(message = "Name is required")
        String name,

        Long parentId,

        @NotNull(message = "Project is required")
        @NotBlank(message = "Project is required")
        @NotEmpty(message = "Project is required")
        @Min(value = 1, message = "Project is required")
        Long projectId
) {
}