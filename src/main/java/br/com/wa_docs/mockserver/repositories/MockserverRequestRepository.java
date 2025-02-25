package br.com.wa_docs.mockserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.mockserver.domains.MockserverRequest;

@Repository
public interface MockserverRequestRepository extends JpaRepository<MockserverRequest, Long> {
}