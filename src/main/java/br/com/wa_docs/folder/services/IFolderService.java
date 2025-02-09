package br.com.wa_docs.folder.services;

import java.util.List;

import br.com.wa_docs.folder.domains.Folder;

public interface IFolderService {
    Folder createFolder(Folder folder);
    Folder getFolderById(Long id);
    Folder updateFolder(Folder folder);
    List<Folder> getFolderByProjectId(Long projectId, Long parentId);
}