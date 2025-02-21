package br.com.wa_docs.request.services;

import java.util.List;

import br.com.wa_docs.request.domains.Request;

public interface IRequestService {
    Long save(Request request);

    Long update(Request request);

    void delete(Long requestId);

    void deleteMany(Long[] requestIds);

    Request findById(Long id);

    List<Request> findByFolderId(Long folderId);

    Request execute(Request request);
}