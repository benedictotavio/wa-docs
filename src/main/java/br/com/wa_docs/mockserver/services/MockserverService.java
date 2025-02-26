package br.com.wa_docs.mockserver.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mockserver.client.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Headers;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Parameters;
import org.springframework.stereotype.Service;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.repositories.MockserverRepository;

@Service
public class MockserverService implements IMockserverService {

    private final MockserverRepository mockserverRepository;
    private final MockServerClient mockServerClient;

    public MockserverService(MockserverRepository mockserverRepository) {
        this.mockserverRepository = mockserverRepository;
        this.mockServerClient = new MockServerClient(
                "localhost",
                1080);
    }

    @Override
    public Mockserver create(Mockserver mockserver) {
        Mockserver mockserverSaved = this.mockserverRepository.save(mockserver);
        this.createMock(mockserverSaved);
        return mockserverSaved;
    }

    @Override
    public Mockserver findById(Long id) {
        return this.mockserverRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Mockserver not found"));
    }

    @Override
    public void delete(Long id) {
        this.mockserverRepository.deleteById(id);
    }

    @Override
    public Mockserver update(Mockserver mockserver) {
        return mockserverRepository.save(mockserver);
    }

    @Override
    public List<Mockserver> findByProjectId(Long projectId) {
        return this.mockserverRepository.findByProjectId(projectId);
    }

    private Headers convertStringToHeaders(String headers) {
        if (headers == null || headers.isEmpty()) {
            return null;
        }
        headers = headers.trim().substring(1, headers.length() - 1).trim();
        String[] headersArray = headers.split(",\\s*");
        Headers headersObject = new Headers();
        for (String header : headersArray) {
            String[] headerParts = header.split(":\\s*");
            if (headerParts.length == 2) {
                headersObject.withEntry(
                        headerParts[0].trim().replace("\"", ""),
                        headerParts[1].trim().replace("\"", ""));
            }
        }
        return headersObject;
    }

    private Parameters convertStringToQueryParams(String queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return null;
        }
        Parameters queryParamsObject = new Parameters();
        String[] queryParamsArray = queryParams.split("\n");
        for (String queryParam : queryParamsArray) {
            String[] queryParamParts = queryParam.split(":");
            queryParamsObject.withEntry(
                    queryParamParts[0].trim(),
                    queryParamParts[1]);
        }
        return queryParamsObject;
    }

    private void createMock(Mockserver mockserver) {
        mockServerClient.when(
                HttpRequest.request().withMethod(
                        mockserver.getRequest().getMethod().name()).withPath(
                                mockserver.getRequest().getPath())
                        .withBody(
                                mockserver.getRequest().getBody())
                        .withHeaders(
                                this.convertStringToHeaders(
                                        mockserver.getRequest().getHeaders()))
                        .withQueryStringParameters(
                                this.convertStringToQueryParams(
                                        mockserver.getRequest().getQueryParams())))
                .respond(
                        HttpResponse.response()
                                .withStatusCode(mockserver.getResponse().getStatusCode().getValue())
                                .withBody(mockserver.getResponse().getBody())
                                .withHeaders(
                                        this.convertStringToHeaders(
                                                mockserver.getResponse().getHeaders()))
                                .withDelay(
                                        Delay.delay(
                                                TimeUnit.SECONDS,
                                                1)));

    }
}
