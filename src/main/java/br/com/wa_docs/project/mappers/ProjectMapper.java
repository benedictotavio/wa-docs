package br.com.wa_docs.project.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.wa_docs.folder.dtos.ResponseFolderDto;
import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;
import br.com.wa_docs.project.dtos.ResponseProjectDto;
import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.user.domains.User;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static Project toProject(CreateProjectDto createProject, Team team, User owner) {
        return new Project(
                createProject.name(),
                createProject.description(),
                team,
                owner);
    }

    public static ResponseProjectDto toResponseProjectDto(Project project) {

        List<ResponseFolderDto> folders = new ArrayList<>();

        for (var folder : project.getFolders()) {

            if (folder.getParent() == null) {
                folders.add(new ResponseFolderDto(
                    folder.getId(),
                    folder.getName(),
                    null,
                    folder.getProject().getId(),
                    folder.getLevel()
                ));
                continue;
            }

            folders.add(new ResponseFolderDto(
                folder.getId(),
                folder.getName(),
                folder.getParent().getId(),
                folder.getProject().getId(),
                folder.getLevel()
            ));
        }

        return new ResponseProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                folders
                );
    }
}
