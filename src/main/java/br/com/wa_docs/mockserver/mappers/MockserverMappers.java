package br.com.wa_docs.mockserver.mappers;

import org.springframework.stereotype.Component;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.dtos.CreateMockserverDto;
import br.com.wa_docs.project.services.IProjectService;

@Component
public class MockserverMappers {
    private final IProjectService projectService;

    public MockserverMappers(IProjectService projectService) {
        this.projectService = projectService;
    }

    public Mockserver toMockserver(CreateMockserverDto createMockserverDto) {
        return new Mockserver(
            createMockserverDto.name(),
            createMockserverDto.uri(),
            createMockserverDto.method(),
            createMockserverDto.body(),
            createMockserverDto.headers(),
            createMockserverDto.response(),
            this.projectService.getProjectById(createMockserverDto.projectId())
        );
    }
}
