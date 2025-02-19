package br.com.wa_docs.user.auth.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        var authentication = event.getAuthentication();
        log.info("Usuário {} autenticado com sucesso", authentication.getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        log.info("Falha na autenticação do usuário {}", event.getException().getMessage());
    }
}