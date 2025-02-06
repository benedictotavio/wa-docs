package br.com.wa_docs.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.project.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
