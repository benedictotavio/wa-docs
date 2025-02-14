package br.com.wa_docs.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.folder.services.IFolderService;
import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;
import br.com.wa_docs.project.mappers.ProjectMapper;
import br.com.wa_docs.project.repositories.ProjectRepository;
import br.com.wa_docs.team.services.ITeamService;
import br.com.wa_docs.user.services.IUserService;

@Service
public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;
    private final IFolderService folderService;
    private final ITeamService teamService;
    private final IUserService userService;

    public ProjectService(ProjectRepository projectRepository, IFolderService folderService,
            ITeamService teamService, IUserService userService) {
        this.projectRepository = projectRepository;
        this.folderService = folderService;
        this.teamService = teamService;
        this.userService = userService;
    }

    @Override
    public Project createProject(CreateProjectDto createProject) {
        Project project = ProjectMapper.toProject(createProject, teamService.getTeamById(createProject.teamId()),
                userService.getUserById(createProject.ownerId()));
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void addFolderToProject(Long projectId, String folderName) {
        Project project = this.getProjectById(projectId);
        Folder folder = this.folderService.createFolder(
            new Folder(folderName, null, project)
        );
        project.addFolder(folder);
        projectRepository.save(project);
    }

    @Override
    public List<Project> getProjectByOwner(Long owner) {
        return projectRepository.findByOwnerId(owner);
     }

}
