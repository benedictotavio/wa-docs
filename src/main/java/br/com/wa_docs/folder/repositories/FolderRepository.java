package br.com.wa_docs.folder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wa_docs.folder.domains.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
