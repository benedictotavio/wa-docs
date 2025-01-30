package br.com.wa_docs.team.domains;

import java.time.LocalDate;

import br.com.wa_docs.user.domains.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private User owner;

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
