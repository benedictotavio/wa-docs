package br.com.wa_docs.mockserver.dtos;

import br.com.wa_docs.request.enums.HttpMethod;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateMockserverDto(
        @NotBlank(message = "Name is required")
        @NotNull(message = "Name is required")
        @NotEmpty(message = "Name is required")
        String name,

        @NotBlank(message = "Path is required")
        @NotNull(message = "Path is required")
        @NotEmpty(message = "Path is required")
        String path,
        
        String queryParams,

        @NotNull(message = "Method is required")
        @NotEmpty(message = "Method is required")
        @NotBlank(message = "Method is required")
        HttpMethod method,

        String body,

        String headers,

        @NotNull(message = "ProjectId is required")
        @NotEmpty(message = "ProjectId is required")
        @NotBlank(message = "ProjectId is required")
        Long projectId,

        @NotNull(message = "StatusCode is required")
        @NotEmpty(message = "StatusCode is required")
        @NotBlank(message = "StatusCode is required")
        @Min(value = 100, message = "StatusCode must be greater than 100")
        @Max(value = 599, message = "StatusCode must be less than 599")
        Integer statusCode,

        @NotNull(message = "BodyResponse is required")
        @NotEmpty(message = "BodyResponse is required")
        @NotBlank(message = "BodyResponse is required")
        String bodyResponse
        ) {
}