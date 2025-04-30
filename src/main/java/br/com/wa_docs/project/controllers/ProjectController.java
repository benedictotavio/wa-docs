package br.com.wa_docs.project.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;
import br.com.wa_docs.project.dtos.ProjectResponseDefaultDto;
import br.com.wa_docs.project.dtos.ResponseProjectDto;
import br.com.wa_docs.project.mappers.ProjectMapper;
import br.com.wa_docs.project.services.IProjectService;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDefaultDto> createProject(@RequestBody CreateProjectDto createProject) {
        try {
            Project project = projectService.createProject(createProject);
            return ResponseEntity.created(
                    URI.create("/api/v1/project/")).body(
                            new ProjectResponseDefaultDto(project.getId(), "Project created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ProjectResponseDefaultDto(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProjectDto> getProjectById(@PathVariable Long id) {
        Project project = this.projectService.getProjectById(id);
        ResponseProjectDto responseProjectDto = ProjectMapper.toResponseProjectDto(project);
        return ResponseEntity.ok(responseProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponseDefaultDto> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok(new ProjectResponseDefaultDto("Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ProjectResponseDefaultDto(e.getMessage()));
        }
    }

    public record CreateFolderToProject(
            String folderName,
            Integer level) {
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDefaultDto> addFolderToProject(
            @PathVariable Long projectId,
            @RequestBody CreateFolderToProject createFolderToProject) {
        try {
            projectService.addFolderToProject(projectId,
                    createFolderToProject.folderName(),
                    createFolderToProject.level());
            return ResponseEntity.ok(new ProjectResponseDefaultDto("Folder added to project successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ProjectResponseDefaultDto(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseProjectDto>> getProjectByOwner(
            @RequestParam(required = false, name = "owner") Long ownerId,
            @RequestParam(required = false, name = "team") Long teamId) {
        List<Project> projects = projectService.filterProjects(ownerId, teamId);
        return ResponseEntity.ok(
                projects.stream().map(ProjectMapper::toResponseProjectDto).toList());
    }
}
