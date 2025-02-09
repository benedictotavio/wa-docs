package br.com.wa_docs.request.domains;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.request.enums.HttpMethod;
import jakarta.persistence.CascadeType;
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
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String uri;

    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    private String body;

    private String headers;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    public Request(String name, String uri, HttpMethod method, String body, String headers, Folder folder) {
        this.name = name;
        this.uri = uri;
        this.method = method;
        this.body = body;
        this.headers = headers;
        this.folder = folder;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }

        this.name = name;
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
}
