package br.com.wa_docs.folder.dtos;

public record UpdateFolderDto(
        String name,
        Long parentId) {

    public UpdateFolderDto(String name) {
        this(name, null);
    }
}
