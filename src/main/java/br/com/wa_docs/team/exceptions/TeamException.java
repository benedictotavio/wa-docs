package br.com.wa_docs.team.exceptions;

public abstract class TeamException extends RuntimeException {
    TeamException(String message) {
        super(message);
    }
}
