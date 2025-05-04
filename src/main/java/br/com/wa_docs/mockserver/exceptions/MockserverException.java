package br.com.wa_docs.mockserver.exceptions;

public abstract class MockserverException extends RuntimeException {
    MockserverException(String message) {
        super(message);
    }
}
