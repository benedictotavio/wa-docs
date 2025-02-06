package br.com.wa_docs.project.services;

import org.springframework.stereotype.Service;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;
import br.com.wa_docs.project.mappers.ProjectMapper;
import br.com.wa_docs.project.repositories.ProjectRepository;
import br.com.wa_docs.team.services.TeamService;
import br.com.wa_docs.user.services.UserService;

@Service
public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;
    private final TeamService teamService;
    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, TeamService teamService, UserService userService) {
        this.projectRepository = projectRepository;
        this.teamService = teamService;
        this.userService = userService;
    }

    @Override
    public Project createProject(CreateProjectDto createProject) {
        Project project = ProjectMapper.toProject(createProject, teamService.getTeamById(createProject.teamId()),
                userService.getUserById(createProject.ownerId()));
        return projectRepository.save(project);
    }

}
