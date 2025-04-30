package br.com.wa_docs.folder.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.folder.dtos.CreateFolderDto;
import br.com.wa_docs.folder.dtos.ResponseFolderDefaultDto;
import br.com.wa_docs.folder.dtos.ResponseFolderDto;
import br.com.wa_docs.folder.dtos.UpdateFolderDto;
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

    @PostMapping
    public ResponseEntity<ResponseFolderDefaultDto> createFolder(@RequestBody CreateFolderDto createFolderDto) {
        try {
            Folder folder = this.folderMappers.toFolder(createFolderDto);
            Folder createdFolder = this.folderService.createFolder(folder);
            return ResponseEntity.ok(new ResponseFolderDefaultDto(createdFolder.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseFolderDefaultDto(e.getMessage()));
        }
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

    @GetMapping("/tree")
    public ResponseEntity<List<ResponseFolderDto>> getFolderTree(
            @RequestParam Long projectId,
            @RequestParam(required = false) Long parentId) {
        List<Folder> folders = this.folderService.getFolderByProjectId(projectId, parentId);
        List<ResponseFolderDto> responseFolderDtos = new ArrayList<>();
        for (Folder folder : folders) {
            responseFolderDtos.add(
                    this.folderMappers.toResponseFolderDto(folder));
        }
        return ResponseEntity.ok(responseFolderDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseFolderDefaultDto> updateFolder(@PathVariable Long id,
            @RequestBody UpdateFolderDto updateFolderDto) {
        try {
            Folder folder = this.folderMappers.toFolder(updateFolderDto);
            folder.setId(id);
            Folder updatedFolder = this.folderService.updateFolder(folder);
            return ResponseEntity.ok(
                    new ResponseFolderDefaultDto(
                            updatedFolder.getId(),
                            updatedFolder.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseFolderDefaultDto> deleteFolder(@PathVariable Long id) {
        try {
            this.folderService.deleteFolder(id);
            return ResponseEntity.ok(new ResponseFolderDefaultDto("Folder deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
