package br.com.wa_docs.project.services;

import java.util.List;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;

public interface IProjectService {
    Project createProject(CreateProjectDto createProject);

    Project getProjectById(Long id);

    void deleteProject(Long id);

    void addFolderToProject(Long projectId, String folderName, Integer level);

    List<Project> getProjectByOwner(Long owner);

    List<Project> filterProjects(Long owner, Long team);
}
