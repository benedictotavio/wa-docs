package br.com.wa_docs.user.auth.domains;

import org.springframework.security.core.GrantedAuthority;

import br.com.wa_docs.user.auth.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Entity
@Table
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "role_id")
  private Integer roleId;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole authority;

  public Role(ERole authority) {
    this.authority = authority;
  }

  public void setAuthority(ERole authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return this.authority.getRole();
  }

}