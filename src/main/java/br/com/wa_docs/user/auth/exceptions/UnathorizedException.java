package br.com.wa_docs.user.auth.exceptions;

public class UnathorizedException extends AuthException {
    public UnathorizedException(String message) {
        super(message);
    }
}
