package com.example.authentication_service.autentication;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authentication_service.dto.LoginRequestDto;
import com.example.authentication_service.dto.TokenDto;
import com.example.authentication_service.util.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AutenticationController 
{
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AutenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils)
    {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAuthentication(@RequestBody LoginRequestDto loginRequest) 
    {
        try 
        {
            UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(loginData);
            String jwt = jwtUtils.createToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok().body(jwt);
        }
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Credenciales incorrectas. Porfavor reintente nuevamente");
        }
    }

    
    @PostMapping("/testingJWT")
    public ResponseEntity<String> decodificando(@RequestBody TokenDto token)
    {
        try
        {
            DecodedJWT decodedJWT = jwtUtils.decodedJWT(token.getToken());
            return ResponseEntity.ok().body(decodedJWT.getClaims().toString());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No tiene autorizacion para acceder a este recurso. ");
        }
    }
        
}

