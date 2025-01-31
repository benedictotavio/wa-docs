package br.com.wa_docs.user.auth.services;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.wa_docs.user.auth.dtos.login.LoginResponseDto;
import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.auth.dtos.signup.SignUpResponseDto;
import br.com.wa_docs.user.auth.exceptions.PasswordsNotEqualsException;
import br.com.wa_docs.user.auth.exceptions.UnathorizedException;
import br.com.wa_docs.user.auth.exceptions.UserAlreadyRegisteredException;
import br.com.wa_docs.user.auth.mappers.AuthMappers;
import br.com.wa_docs.user.auth.security.JwtService;
import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.repositories.UserRepository;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequest) {

        if (!signUpRequest.password().equals(signUpRequest.confirmPassword())) {
            throw new PasswordsNotEqualsException("Senhas não conferem");
        }

        String email = signUpRequest.email();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException("Usuário já cadastrado");
        }

        User newUser = AuthMappers.toUser(signUpRequest);

        newUser.setPassword(this.passwordEncoder.encode(signUpRequest.password()));

        this.userRepository.save(newUser);

        return new SignUpResponseDto("Usuário criado com sucesso.", email);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return user.get();
    }

    @Override
    public LoginResponseDto login(User user) {
        try {

            String tokenAuth = this.jwtService.generateToken(user);

            return new LoginResponseDto(tokenAuth);

        } catch (Exception e) {
            throw new UnathorizedException("Usuário ou senha inválidos");
        }
    }
}