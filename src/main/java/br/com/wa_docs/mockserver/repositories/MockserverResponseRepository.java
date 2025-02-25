package br.com.wa_docs.mockserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.mockserver.domains.MockserverResponse;

@Repository
public interface MockserverResponseRepository extends JpaRepository<MockserverResponse, Long> {
}