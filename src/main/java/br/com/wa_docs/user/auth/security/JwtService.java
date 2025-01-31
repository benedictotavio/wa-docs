package br.com.wa_docs.user.auth.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.wa_docs.user.domains.User;

@Service
public class JwtService {

    private JwtProperties jwtProperties;

    public String generateToken(User user) {
        try {
            // Define o algoritmo HMAC SHA256 para criar a assinatura do token passando a
            // chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
            return JWT.create()
                    .withIssuer(jwtProperties.getIssuer()) // Define o emissor do token
                    .withIssuedAt(this.creationDate()) // Define a data de emissão do token
                    .withExpiresAt(this.expirationDate()) // Define a data de expiração do token
                    .withSubject(user.getUsername()) // Define o assunto do token (neste caso, o nome de usuário)
                    .sign(algorithm); // Assina o token usando o algoritmo especificado
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando
            // a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
            return JWT.require(algorithm)
                    .withIssuer(jwtProperties.getIssuer()) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token \n" + exception.getMessage());
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }
}
