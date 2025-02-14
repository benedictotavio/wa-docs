package br.com.wa_docs.team.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wa_docs.team.domains.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByOwnerId(Long ownerId);
}
