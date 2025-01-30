package br.com.wa_docs.user.auth.exceptions;

public class UserAlreadyRegisteredException extends AuthException {
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
