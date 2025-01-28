package br.com.wa_docs.user.domains;

import br.com.wa_docs.user.enums.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Enumerated(EnumType.STRING)
  private RoleName role;

}