package br.com.wa_docs.user.enums;

public enum RoleName {
    ADMIN("admin"),
    USER("user");
  
    private String role;
  
    RoleName(String role) {
      this.role = role;
    }
  
    public String getValue() {
      return role;
    }
  }
