package br.com.wa_docs.user.domains;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.user.auth.domains.ProjectRole;
import br.com.wa_docs.user.auth.domains.Role;
import br.com.wa_docs.user.auth.enums.ERole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Team> team;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProjectRole> projectRoles;

    @Column(name = "created_at", nullable = true, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDate updatedAt;

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.projectRoles = new HashSet<>();
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.role = new Role(ERole.valueOf(role.toUpperCase()));
    }

    public ProjectRole addProjectRole(ProjectRole projectRole) {
        this.projectRoles.add(projectRole);
        return projectRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.equals(this.role.getAuthority(), ERole.ADMIN.getRole())) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
