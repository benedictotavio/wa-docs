package br.com.wa_docs.request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wa_docs.request.domains.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
