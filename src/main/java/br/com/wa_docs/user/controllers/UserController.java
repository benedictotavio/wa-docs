package br.com.wa_docs.user.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.dtos.UserResponseDto;
import br.com.wa_docs.user.mappers.UserMapper;
import br.com.wa_docs.user.services.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
        return ResponseEntity.ok(
                UserMapper.toUserResponseDto(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUser(@RequestParam String email) {
        try {
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(
                    UserMapper.toUserResponseDto(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
