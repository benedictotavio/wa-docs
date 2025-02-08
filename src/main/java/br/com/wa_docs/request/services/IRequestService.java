package br.com.wa_docs.request.services;

import br.com.wa_docs.request.domains.Request;

public interface IRequestService {
    void save(Request request);

    void update(Request request);

    void delete(Long requestId);

    void deleteMany(Long[] requestIds);

    Request findById(Long id);
}