package br.com.wa_docs.user.auth.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wa_docs.user.auth.dtos.signup.SignUpRequest;
import br.com.wa_docs.user.auth.exceptions.PasswordsNotEqualsException;
import br.com.wa_docs.user.auth.mappers.AuthMappers;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.repositories.UserRepository;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        if (!signUpRequest.password().equals(signUpRequest.confirmPassword())) {
            throw new PasswordsNotEqualsException("Senhas não conferem");
        }

        String email = signUpRequest.email();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado"); // TODO: Criar exceção personalizada no modulo utils
        }

        User newUser = AuthMappers.toUser(signUpRequest);
        newUser.setPassword(
            new BCryptPasswordEncoder().encode(signUpRequest.password())
        );

        userRepository.save(newUser);
    }
}