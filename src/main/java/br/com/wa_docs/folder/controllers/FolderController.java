package br.com.wa_docs.folder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.folder.dtos.CreateFolderDto;
import br.com.wa_docs.folder.dtos.ResponseFolderDefaultDto;
import br.com.wa_docs.folder.dtos.ResponseFolderDto;
import br.com.wa_docs.folder.mappers.FolderMappers;
import br.com.wa_docs.folder.services.IFolderService;

@RestController
@RequestMapping("/api/v1/folder")
public class FolderController {

    private final IFolderService folderService;
    private final FolderMappers folderMappers;

    public FolderController(IFolderService folderService, FolderMappers folderMappers) {
        this.folderService = folderService;
        this.folderMappers = folderMappers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFolderDto> getFolderById(@PathVariable Long id) {
        Folder folder = this.folderService.getFolderById(id);
        return ResponseEntity.ok(this.folderMappers.toResponseFolderDto(folder));
    }

    @PostMapping("/subfolder")
    public ResponseEntity<ResponseFolderDefaultDto> createSubfolder(@RequestBody CreateFolderDto createFolderDto) {
        try {
            Folder folder = this.folderMappers.toFolder(createFolderDto);
            Folder createdFolder = this.folderService.createFolder(folder);
            return ResponseEntity.ok(
                    new ResponseFolderDefaultDto(
                            createdFolder.getId(),
                            createdFolder.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
