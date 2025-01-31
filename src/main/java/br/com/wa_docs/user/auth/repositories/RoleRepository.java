package br.com.wa_docs.user.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(ERole authority);
}
