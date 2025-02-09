package br.com.wa_docs.user.mappers;

import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.dtos.UserResponseDto;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getAuthority());
    }
}