package br.com.wa_docs.mockserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wa_docs.mockserver.domains.Mockserver;

public interface MockserverRepository extends JpaRepository<Mockserver, Long> {
    @Query("SELECT m FROM Mockserver m WHERE m.project.id = :projectId")
    List<Mockserver> findByProjectId(Long projectId);
}