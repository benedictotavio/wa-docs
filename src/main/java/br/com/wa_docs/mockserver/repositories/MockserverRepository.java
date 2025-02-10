package br.com.wa_docs.mockserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wa_docs.mockserver.domains.Mockserver;

public interface MockserverRepository extends JpaRepository<Mockserver, Long> {
}