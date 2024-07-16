package com.example.authentication_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication_service.feignClients.modelsFeignClients.UsuarioModel;
import com.example.authentication_service.service.UsuarioService;

@RestController
@RequestMapping("/auth/home")
public class UsuarioController 
{
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() 
    {
        List<UsuarioModel> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) 
        {
            System.out.println("paso por aqui EN EL EMPTY");
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            System.out.println("paso por aqui");
            return ResponseEntity.ok().body(usuarios);
        }

    }

}
