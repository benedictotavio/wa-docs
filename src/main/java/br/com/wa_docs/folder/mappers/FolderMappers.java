package br.com.wa_docs.folder.mappers;

import org.springframework.stereotype.Component;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.folder.dtos.CreateFolderDto;
import br.com.wa_docs.folder.dtos.ResponseFolderDto;
import br.com.wa_docs.folder.services.IFolderService;
import br.com.wa_docs.project.services.IProjectService;

@Component
public class FolderMappers {
    private final IProjectService projectService;
    private final IFolderService folderService;

    public FolderMappers(IProjectService projectService, IFolderService folderService) {
        this.projectService = projectService;
        this.folderService = folderService;
    }

    public Folder toFolder(CreateFolderDto createFolderDto) {
        return new Folder(createFolderDto.name(),
                folderService.getFolderById(createFolderDto.parentId()),
                projectService.getProjectById(createFolderDto.projectId()));
    }

    public ResponseFolderDto toResponseFolderDto(Folder folder) {
        if (folder.getParent() == null) {
            return new ResponseFolderDto(folder.getId(), folder.getName(), folder.getProject().getId());
        }
        return new ResponseFolderDto(folder.getId(), folder.getName(), folder.getParent().getId(),
                folder.getProject().getId());
    }

}
