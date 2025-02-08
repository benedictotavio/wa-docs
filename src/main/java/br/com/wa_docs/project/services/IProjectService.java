package br.com.wa_docs.project.services;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;

public interface IProjectService {
    Project createProject(CreateProjectDto createProject);
    Project getProjectById(Long id);
    void deleteProject(Long id);
}
