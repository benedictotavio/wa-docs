package br.com.wa_docs.team.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.CreateTeamDto;
import br.com.wa_docs.team.dtos.ResponseTeamDefaultDto;
import br.com.wa_docs.team.dtos.ResponseTeamDto;
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
    public ResponseEntity<ResponseTeamDto> getTeamById(@PathVariable Long id) {
        Team team = this.teamService.getTeamById(id);
        return ResponseEntity.ok(TeamMappers.toResponseTeamDto(team));
    }

    @PostMapping
    public ResponseEntity<ResponseTeamDefaultDto> createTeam(@RequestBody CreateTeamDto createTeamDto) {
        try {
            Team newTeam = this.teamService.createTeam(createTeamDto);
            return ResponseEntity.created(
                    URI.create("/api/v1/team/")).body(
                            new ResponseTeamDefaultDto(newTeam.getTeamId(), "Team created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseTeamDefaultDto(null, e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseTeamDefaultDto> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamDto team) {
        try {
            this.teamService.updateTeam(id, new Team(team.name()));
            return ResponseEntity.ok(
                    new ResponseTeamDefaultDto(id, "Team updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTeamDefaultDto> deleteTeam(@PathVariable Long id) {
        try {
            this.teamService.deleteTeam(id);
            return ResponseEntity.ok(
                    new ResponseTeamDefaultDto(id, "Team deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<ResponseTeamDefaultDto> deleteManyTeams(@RequestBody Long[] ids) {
        try {
            this.teamService.deleteManyTeams(ids);
            return ResponseEntity.ok(
                    new ResponseTeamDefaultDto(null, "Teams deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
