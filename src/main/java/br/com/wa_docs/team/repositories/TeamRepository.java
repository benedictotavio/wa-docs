package br.com.wa_docs.team.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wa_docs.team.domains.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    
}
