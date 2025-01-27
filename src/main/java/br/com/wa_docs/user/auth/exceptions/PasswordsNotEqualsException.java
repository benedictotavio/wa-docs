package br.com.wa_docs.user.auth.exceptions;

public class PasswordsNotEqualsException extends RuntimeException {
    public PasswordsNotEqualsException(String message) {
        super(message);
    }
}
