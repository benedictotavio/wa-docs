package br.com.wa_docs.folder.services;

import br.com.wa_docs.folder.domains.Folder;

public interface IFolderService {
    Folder createFolder(Folder folder);
    Folder getFolderById(Long id);
}