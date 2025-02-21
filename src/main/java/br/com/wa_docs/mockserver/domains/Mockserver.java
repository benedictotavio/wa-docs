package br.com.wa_docs.mockserver.domains;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.request.enums.HttpMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "mockservers")
public class Mockserver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String uri;

    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    private String body;

    private String headers;

    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Mockserver(String name, String uri, HttpMethod method, String body, String headers, String response,
            Project project) {
        this.name = name;
        this.uri = uri;
        this.method = method;
        this.body = body;
        this.headers = headers;
        this.response = response;
        this.project = project;
    }

    public void setUri(String uri) {
        if (uri == null || uri.isEmpty()) {
            return;
        }
        this.uri = uri;
    }

    public void setMethod(HttpMethod method) {
        if (method == null) {
            return;
        }
        this.method = method;
    }

    public void setBody(String body) {
        if (body == null || body.isEmpty()) {
            return;
        }
        this.body = body;
    }

    public void setHeaders(String headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        this.headers = headers;
    }

    public void setResponse(String response) {
        if (response == null) {
            return;
        }
        this.response = response;
    }
}