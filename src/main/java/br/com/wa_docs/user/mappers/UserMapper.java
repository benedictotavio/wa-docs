package br.com.wa_docs.user.mappers;

import java.util.HashSet;
import java.util.Set;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.ResponseTeamDto;
import br.com.wa_docs.team.mappers.TeamMappers;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.dtos.UserResponseDto;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponseDto toUserResponseDto(User user) {

        Set<ResponseTeamDto> responseTeamDto = new HashSet<>();
        for (Team team : user.getTeam()) {
            responseTeamDto.add(TeamMappers.toResponseTeamDto(team));
        }

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getAuthority(),
                responseTeamDto);
    }
}