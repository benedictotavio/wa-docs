package br.com.wa_docs.folder.services;

import org.springframework.stereotype.Service;

import br.com.wa_docs.folder.domains.Folder;
import br.com.wa_docs.folder.repositories.FolderRepository;

@Service
public class FolderService implements IFolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public Folder createFolder(Folder folder) {
        return this.folderRepository.save(folder);
    }

    @Override
    public Folder getFolderById(Long id) {
        return this.folderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Folder not found"));
    }

}
