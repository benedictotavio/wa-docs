package br.com.wa_docs.mockserver.domains;

import br.com.wa_docs.project.domain.Project;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mockservers")
public class Mockserver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // Todo: delete all requests when delete mockserver
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private MockserverRequest request;

    //Todo: delete all responses when delete mockserver
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    private MockserverResponse response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Mockserver(String name, MockserverRequest request, MockserverResponse response, Project project) {
        this.name = name;
        this.request = request;
        this.response = response;
        this.project = project;
    }

    public void setRequest(MockserverRequest request) {
        if (request == null) {
            return;
        }
        this.request = request;
    }

    public void setResponse(MockserverResponse response) {
        if (response == null) {
            return;
        }
        this.response = response;
    }

    public void setProject(Project project) {
        if (project == null) {
            return;
        }
        this.project = project;
    }
}