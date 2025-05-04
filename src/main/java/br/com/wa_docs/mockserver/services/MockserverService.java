package br.com.wa_docs.mockserver.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mockserver.client.MockServerClient;
import org.mockserver.mock.Expectation;
import org.mockserver.model.Delay;
import org.mockserver.model.Headers;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.wa_docs.mockserver.configs.MockserverProperties;
import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.exceptions.MockserverNotFound;
import br.com.wa_docs.mockserver.repositories.MockserverRepository;

@Service
public class MockserverService implements IMockserverService {

    private final MockserverRepository mockserverRepository;
    private final MockServerClient mockServerClient;
    private final MockserverProperties mockserverProperties;

    public MockserverService(MockserverRepository mockserverRepository, MockserverProperties mockserverProperties) {
        this.mockserverRepository = mockserverRepository;
        this.mockserverProperties = mockserverProperties;
        this.mockServerClient = new MockServerClient(
                this.mockserverProperties.getHost(),
                this.mockserverProperties.getPort());
    }

    @Override
    public Mockserver create(Mockserver mockserver, Boolean asRestTemplate) {

        if (Boolean.TRUE.equals(asRestTemplate) || asRestTemplate != null) {
            this.createMockByRestTemplate(mockserver);
            return mockserver;
        }

        String mock = this.createMock(mockserver);
        mockserver.setEspectationId(mock);
        mockserver.setBaseUrl(this.mockServerClient.remoteAddress().getHostString());
        return this.mockserverRepository.save(mockserver);
    }

    @Override
    public Mockserver findById(Long id) {
        return this.mockserverRepository.findById(id).orElseThrow(
                () -> new MockserverNotFound("Mockserver not found"));
    }

    @Override
    public void delete(Long id) {
        Mockserver mockserver = this.findById(id);
        try {
            this.mockServerClient.clear(
                    HttpRequest.request()
                            .withPath(mockserver.getRequest().getPath()));
        } catch (Exception e) {
            throw new MockserverNotFound("Mockserver not found");
        }
        this.mockserverRepository.delete(mockserver);
    }

    @Override
    public Mockserver update(Mockserver mockserver) {
        return mockserverRepository.save(mockserver);
    }

    @Override
    public List<Mockserver> findByProjectId(Long projectId) {
        return this.mockserverRepository.findByProjectId(projectId);
    }

    @Override
    public void restartMockServer() {
        if (this.mockServerClient.hasStarted()) {
            this.mockServerClient.reset();
        }
    }

    @Override
    public void stopMockServer() {
        if (this.mockServerClient.hasStarted()) {
            this.mockServerClient.stop();
        }
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

    private String createMock(Mockserver mockserver) {
        HttpRequest request = new HttpRequest()
                .withBody(mockserver.getRequest().getBody())
                .withMethod(mockserver.getRequest().getMethod().getValue())
                .withPath(mockserver.getRequest().getPath())
                .withQueryStringParameters(convertStringToQueryParams(mockserver.getRequest().getQueryParams()))
                .withHeaders(convertStringToHeaders(mockserver.getRequest().getHeaders()));
        HttpResponse response = new HttpResponse()
                .withStatusCode(mockserver.getResponse().getStatusCode().getValue())
                .withBody(mockserver.getResponse().getBody())
                .withHeaders(convertStringToHeaders(mockserver.getResponse().getHeaders()))
                .withDelay(Delay.delay(
                        TimeUnit.SECONDS,
                        1));

        try {
            Expectation[] results = mockServerClient.when(request).respond(response);
            return results[0].getId();
        } catch (Exception e) {
            return "Error creating mock";
        }
    }

    private void createMockByRestTemplate(Mockserver mockserver) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(
                "http://localhost:" + mockserverProperties.getPort() + mockserver.getRequest().getPath(),
                mockserver,
                Mockserver.class);
    }
}
