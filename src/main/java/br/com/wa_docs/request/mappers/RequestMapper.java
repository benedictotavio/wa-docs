package br.com.wa_docs.request.mappers;

import org.springframework.stereotype.Component;

import br.com.wa_docs.project.services.ProjectService;
import br.com.wa_docs.request.domains.Request;
import br.com.wa_docs.request.dtos.CreateRequestDto;
import br.com.wa_docs.request.dtos.ResponseRequestDto;

@Component
public class RequestMapper {

    private final ProjectService projectService;

    public RequestMapper(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Request toRequest(CreateRequestDto createRequestDto) {
        return new Request(
                createRequestDto.name(),
                createRequestDto.uri(),
                createRequestDto.method(),
                createRequestDto.body(),
                createRequestDto.headers(),
                createRequestDto.response(),
                this.projectService.getProjectById(createRequestDto.projectId()));
    }

    public ResponseRequestDto toResponseRequestDto(Request request) {
        return new ResponseRequestDto(
                request.getName(),
                request.getUri(),
                request.getMethod().getValue(),
                request.getBody(),
                request.getHeaders(),
                request.getResponse()
        );
    }
}
