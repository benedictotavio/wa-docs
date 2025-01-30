package br.com.wa_docs.user.domains;

public enum UserRole {

  ADMIN("admin"),
  USER("user");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}