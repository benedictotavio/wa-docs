package br.com.wa_docs.team.domains;

import java.time.LocalDate;
import java.util.Set;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.user.domains.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    private String name;

    private LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(mappedBy = "team")
    private Set<Project> projects;

    public Team(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.createdAt = LocalDate.now();
    }

    public void setName(String name) {

        if (name == null || name.isEmpty()) {
            return;
        }

        this.name = name;
    }


    public void setOwner(User owner) {

        if (owner == null) {
            return;
        }

        this.owner = owner;
    }
}
