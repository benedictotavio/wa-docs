package br.com.wa_docs.mockserver.domains;

import br.com.wa_docs.mockserver.enums.BodyFormat;
import br.com.wa_docs.mockserver.enums.StatusCode;
import jakarta.persistence.CascadeType;
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
@Table(name = "mockservers_responses")
@AllArgsConstructor
@NoArgsConstructor
public class MockserverResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    private String body;

    private BodyFormat bodyFormat;

    private String headers;

    @OneToOne(mappedBy = "response", cascade = CascadeType.ALL)
    private Mockserver mockserver;

    public MockserverResponse(StatusCode statusCode, String body, String headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    public MockserverResponse(Integer statusCode, String body, String headers) {
        this.statusCode = StatusCode.fromValue(statusCode);
        this.body = body;
        this.headers = headers;
    }
    
}
