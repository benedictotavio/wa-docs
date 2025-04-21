package br.com.wa_docs.folder.dtos;

public record ResponseFolderDto(
        Long folderId,
        String folderName,
        Long parentFolderId,
        Long projectId,
        Integer level
        ) {

    public ResponseFolderDto(Long folderId, String folderName, Long projectId, Integer level) {
        this(folderId, folderName, null, projectId, level);
    }
}