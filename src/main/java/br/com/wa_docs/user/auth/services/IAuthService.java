package br.com.wa_docs.user.auth.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import br.com.wa_docs.user.auth.dtos.signup.SignUpRequestDto;
import br.com.wa_docs.user.auth.dtos.signup.SignUpResponseDto;
import br.com.wa_docs.user.domains.User;

public interface IAuthService extends UserDetailsService {
    SignUpResponseDto signUp(SignUpRequestDto signUpRequest);
    String login(User loginRequest);
}
