package com.gestaofacil.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gestaofacil.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generateToken(User user){
        var secret = "123456";
        var algorithm = Algorithm.HMAC256(secret);
        try{
            return JWT
                    .create()
                    .withIssuer("gestao_facil")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getUser_id())
                    .withClaim("company", user.getCompany().getName())
                    .withClaim("name", user.getName())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException("Não foi possível criar o token");
        }
    }

    private Instant getExpirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
