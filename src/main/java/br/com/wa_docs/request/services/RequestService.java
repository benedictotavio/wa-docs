package br.com.wa_docs.request.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wa_docs.request.domains.Request;
import br.com.wa_docs.request.exceptions.NotFoundRequestException;
import br.com.wa_docs.request.repositories.RequestRepository;

@Service
public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Long save(Request request) {
        return this.requestRepository.save(request).getId();
    }

    @Override
    public Long update(Request request) {
        Request requestSaved = this.findById(request.getId());
        requestSaved.setName(request.getName());
        requestSaved.setUri(request.getUri());
        requestSaved.setMethod(request.getMethod());
        requestSaved.setHeaders(request.getHeaders());
        return this.requestRepository.save(request).getId();
    }

    @Override
    public void delete(Long requestId) {
        this.requestRepository.deleteById(requestId);
    }

    @Override
    public Request findById(Long id) {
        return this.requestRepository.findById(id).orElseThrow(
                () -> new NotFoundRequestException("Request not found"));
    }

    @Override
    public void deleteMany(Long[] requestIds) {
        this.requestRepository.deleteAllByIdInBatch(Arrays.asList(requestIds));
    }

    @Override
    public Request execute(Request request) {
        return this.requestRepository.save(request);
    }

    @Override
    public List<Request> findByFolderId(Long folderId) {
        return this.requestRepository.findByFolderId(folderId);
    }
}