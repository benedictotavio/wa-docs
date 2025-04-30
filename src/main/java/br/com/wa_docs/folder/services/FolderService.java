package br.com.wa_docs.folder.services;

import java.util.List;

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

    @Override
    public List<Folder> getFolderByProjectId(Long projectId, Long parentId) {
        if (parentId == null) {
            return this.folderRepository.findByProjectId(projectId);
        }
        return this.folderRepository.findByProjectIdAndParentId(projectId, parentId);
    }

    @Override
    public Folder updateFolder(Folder folder) {
        Folder folderSaved = this.getFolderById(folder.getId());
        folderSaved.setName(folder.getName());
        folderSaved.setParent(folder.getParent());
        return this.folderRepository.save(folderSaved);
    }

    @Override
    public void deleteFolder(Long id) {
        this.folderRepository.deleteById(id);
    }

    @Override
    public List<Folder> listFoldersByParentId(Long projectId, Long parentId) {
        return this.folderRepository.findByProjectIdAndParentId(projectId, parentId);
    }
}
