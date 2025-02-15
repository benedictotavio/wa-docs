package br.com.wa_docs.folder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.folder.domains.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query("SELECT f FROM Folder f WHERE f.project.id = ?1 AND f.parent.id = ?2")
    List<Folder> findByProjectIdAndParentId(Long projectId, Long parentId);

    @Query("SELECT f FROM Folder f WHERE f.project.id = ?1")
    List<Folder> findByProjectId(Long projectId);
}
