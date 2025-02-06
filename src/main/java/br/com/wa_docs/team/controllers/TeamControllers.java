package br.com.wa_docs.team.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.CreateTeamDto;
import br.com.wa_docs.team.dtos.UpdateTeamDto;
import br.com.wa_docs.team.mappers.TeamMappers;
import br.com.wa_docs.team.services.ITeamService;

@RestController
@RequestMapping("api/v1/team")
public class TeamControllers {

    private final ITeamService teamService;

    TeamControllers(ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        try {
            Team team = this.teamService.getTeamById(id);
            return ResponseEntity.ok(TeamMappers.toResponseTeamDto(team));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createTeam(@RequestBody CreateTeamDto createTeamDto) {
        try {
            Team newTeam = this.teamService.createTeam(createTeamDto);
            return ResponseEntity.created(
                    URI.create("/api/v1/team/")).body(newTeam.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamDto team) {
        try {
            Team updatedTeam = this.teamService.updateTeam(id,
                    new Team(
                            team.name()));
            return ResponseEntity.ok(updatedTeam.getName());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
