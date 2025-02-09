package br.com.wa_docs.folder.dtos;

public record ResponseFolderDto(
        Long folderId,
        String folderNanameme,
        Long parentFolderId,
        Long projectId) {

    public ResponseFolderDto(Long folderId, String folderNanameme, Long projectId) {
        this(folderId, folderNanameme, null, projectId);
    }
}