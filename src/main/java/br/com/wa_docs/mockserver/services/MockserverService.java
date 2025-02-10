package br.com.wa_docs.mockserver.services;

import org.springframework.stereotype.Service;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.repositories.MockserverRepository;

@Service
public class MockserverService implements IMockserverService {

    private final MockserverRepository mockserverRepository;

    public MockserverService(MockserverRepository mockserverRepository) {
        this.mockserverRepository = mockserverRepository;
    }

    @Override
    public Mockserver create(Mockserver mockserver) {
        return mockserverRepository.save(mockserver);
    }

    @Override
    public Mockserver findById(Long id) {
        return this.mockserverRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Mockserver not found"));
    }

    @Override
    public void delete(Long id) {
        this.mockserverRepository.deleteById(id);
    }

}
