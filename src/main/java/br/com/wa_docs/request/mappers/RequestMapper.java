package br.com.wa_docs.request.mappers;

import org.springframework.stereotype.Component;

import br.com.wa_docs.folder.services.IFolderService;
import br.com.wa_docs.request.domains.Request;
import br.com.wa_docs.request.dtos.CreateRequestDto;
import br.com.wa_docs.request.dtos.ResponseRequestDto;

@Component
public class RequestMapper {

    private final IFolderService folderService;

    public RequestMapper(IFolderService folderService) {
        this.folderService = folderService;
    }

    public Request toRequest(CreateRequestDto createRequestDto) {
        return new Request(
                createRequestDto.name(),
                createRequestDto.uri(),
                createRequestDto.method(),
                createRequestDto.body(),
                createRequestDto.headers(),
                this.folderService.getFolderById(createRequestDto.folderId())
                );
    }

    public ResponseRequestDto toResponseRequestDto(Request request) {
        return new ResponseRequestDto(
                request.getName(),
                request.getUri(),
                request.getMethod().getValue(),
                request.getBody(),
                request.getHeaders()
        );
    }
}
