package br.com.wa_docs.request.exceptions;

public abstract class RequestException extends RuntimeException {
    protected RequestException(String message) {
        super(message);
    }
}
