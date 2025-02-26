package br.com.wa_docs.mockserver.domains;

import br.com.wa_docs.request.enums.HttpMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mockservers_requests")
@AllArgsConstructor
@NoArgsConstructor
public class MockserverRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    private String path;
    
    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    private String body;

    private String headers;

    private String queryParams;

    @OneToOne(mappedBy = "request")
    private Mockserver mockserver;

    public MockserverRequest(String path, HttpMethod method, String body, String headers, String queryParams) {
        this.path = path;
        this.method = method;
        this.body = body;
        this.headers = headers;
        this.queryParams = queryParams;
    }
}
