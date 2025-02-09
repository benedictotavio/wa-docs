package br.com.wa_docs.folder.domains;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.request.domains.Request;

@Getter
@Entity
@Table(name = "folders")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Folder parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> subFolders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;

    public Folder() {
    }

    public Folder(String name, Project project) {
        this.name = name;
        this.project = project;
        this.subFolders = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public Folder(String name, Folder parent, Project project) {
        this.name = name;
        this.parent = parent;
        this.project = project;
        this.subFolders = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public void addSubFolder(Folder subFolder) {
        subFolders.add(subFolder);
        subFolder.setParent(this);
    }

    public void removeSubFolder(Folder subFolder) {
        subFolders.remove(subFolder);
        subFolder.setParent(null);
    }

    public void setParent(Folder parent) {
        if (parent == null)
            return;
        this.parent = parent;
    }

    public void setName(String name) {
        if (name == null) {
            return;
        }
        this.name = name;
    }

    public void setProject(Project project) {
        if (project == null) {
            return;
        }
        this.project = project;
    }

    public void setRequests(List<Request> requests) {
        if (requests == null) {
            return;
        }
        this.requests = requests;
    }

    public void setId(Long id) {
        if (id == null) {
            return;
        }
        this.id = id;
    }
}
