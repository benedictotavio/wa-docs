package br.com.wa_docs.mockserver.dtos;

import br.com.wa_docs.request.enums.HttpMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateMockserverDto(
        @NotBlank(message = "Name is required")
        @NotNull(message = "Name is required")
        @NotEmpty(message = "Name is required")
        String name,

        @NotBlank(message = "Uri is required")
        @NotNull(message = "Uri is required")
        @NotEmpty(message = "Uri is required")
        String uri,

        @NotNull(message = "Method is required")
        @NotEmpty(message = "Method is required")
        @NotBlank(message = "Method is required")
        HttpMethod method,

        @NotBlank(message = "Body is required")
        @NotNull(message = "Body is required")
        @NotEmpty(message = "Body is required")
        String body,

        @NotBlank(message = "Headers is required")
        @NotNull(message = "Headers is required")
        @NotEmpty(message = "Headers is required")
        String headers,

        @NotNull(message = "ProjectId is required")
        @NotEmpty(message = "ProjectId is required")
        @NotBlank(message = "ProjectId is required")
        Long projectId,

        @NotNull(message = "Response is required")
        @NotEmpty(message = "Response is required")
        @NotBlank(message = "Response is required")
        String response
        ) {
}