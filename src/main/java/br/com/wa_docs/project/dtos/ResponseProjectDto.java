package br.com.wa_docs.project.dtos;

import java.util.List;

import br.com.wa_docs.folder.dtos.ResponseFolderDto;

public record ResponseProjectDto(
                Long id,
                String name,
                String description,
                List<ResponseFolderDto> folders
                ) {
}
