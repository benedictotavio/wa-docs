package br.com.wa_docs.user.auth.enums;

public enum ERole {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    private ERole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
