package br.com.wa_docs.mockserver.services;

import br.com.wa_docs.mockserver.domains.Mockserver;

public interface IMockserverService {
    Mockserver create(Mockserver mockserver);
    Mockserver findById(Long id);
    void delete(Long id);
}
