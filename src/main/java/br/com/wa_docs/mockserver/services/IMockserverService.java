package br.com.wa_docs.mockserver.services;

import java.util.List;

import br.com.wa_docs.mockserver.domains.Mockserver;

public interface IMockserverService {
    Mockserver create(Mockserver mockserver);
    Mockserver findById(Long id);
    List<Mockserver> findByProjectId(Long projectId);
    void delete(Long id);
    Mockserver update(Mockserver mockserver);
    Mockserver createByRestTemplate(Mockserver mockserver);
}
