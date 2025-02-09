package br.com.wa_docs.folder.dtos;

public record ResponseFolderDefaultDto(
        Long id,
        String name,
        Long timestamp) {

    public ResponseFolderDefaultDto(Long id, String name) {
        this(id, name, System.currentTimeMillis());
    }
}