package br.com.wa_docs.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.project.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwnerId(Long ownerId);

    @Query("SELECT p FROM Project p WHERE p.team.id = :teamId")
    List<Project> findByTeamId(Long teamId);
}
