package br.com.wa_docs.user.auth.exceptions;

public abstract class AuthException extends RuntimeException {
    AuthException(String message) {
        super(message);
    }
}