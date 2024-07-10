package com.example.authentication_service.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${spring.security.backend-emisor}")
    private String emisorToken; 

    @Value("${spring.security.jwt.secretKey}")
    private String secretKey ; 


    // este metodo tendra la funcionalidad de crear el jwt de autentiacion
    public String createToken(Authentication authentication) 
    {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

    
        String username = authentication.getName();

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = JWT.create()
                .withIssuer(emisorToken)
                .withSubject(username)
                .withClaim("roles", roles) 
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);

        return token;
    }

    // este metodo se encargara de decodificar el JWT
    public DecodedJWT decodedJWT(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

            JWTVerifier jwtVerifier = JWT.require(algorithm)
            .withIssuer(this.emisorToken)
            .build();
    
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;
        }
        catch(JWTDecodeException e)
        {
            throw new JWTDecodeException("Ha ocurrido un error no tienes autorizacion para acceder a este recurso", e);
        }
     
    }
}
