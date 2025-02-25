package br.com.wa_docs.mockserver.mappers;

import org.springframework.stereotype.Component;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.domains.MockserverRequest;
import br.com.wa_docs.mockserver.domains.MockserverResponse;
import br.com.wa_docs.mockserver.dtos.CreateMockserverDto;
import br.com.wa_docs.mockserver.repositories.MockserverRequestRepository;
import br.com.wa_docs.mockserver.repositories.MockserverResponseRepository;
import br.com.wa_docs.project.services.IProjectService;
import lombok.var;

@Component
public class MockserverMappers {
    private final IProjectService projectService;
    private final MockserverRequestRepository mockserverRequestRepository;
    private final MockserverResponseRepository mockserverResponseRepository;

    public MockserverMappers(IProjectService projectService, MockserverRequestRepository mockserverRequestRepository,
            MockserverResponseRepository mockserverResponseRepository) {
        this.projectService = projectService;
        this.mockserverRequestRepository = mockserverRequestRepository;
        this.mockserverResponseRepository = mockserverResponseRepository;
    }

    public Mockserver toMockserver(CreateMockserverDto createMockserverDto) {

        var request = this.mockserverRequestRepository.save(
                new MockserverRequest(
                        createMockserverDto.path(),
                        createMockserverDto.method(),
                        createMockserverDto.body(),
                        createMockserverDto.headers(),
                        createMockserverDto.queryParams()));

        var response = this.mockserverResponseRepository.save(
                new MockserverResponse(
                        createMockserverDto.statusCode(),
                        createMockserverDto.bodyResponse(),
                        createMockserverDto.headers()));

        return new Mockserver(
                createMockserverDto.name(),
                request,
                response,
                this.projectService.getProjectById(createMockserverDto.projectId()));
    }
}