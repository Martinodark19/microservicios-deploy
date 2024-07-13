package com.example.authentication_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.authentication_service.feignClients.UsuarioFeignClient;
import com.example.authentication_service.feignClients.modelsFeignClients.UsuarioModel;

@Service
public class UsuarioService {
    private UsuarioFeignClient usuarioFeignClient;

    public UsuarioService(UsuarioFeignClient usuarioFeignClient) {
        this.usuarioFeignClient = usuarioFeignClient;
    }

    public List<UsuarioModel> obtenerUsuarios() 
    {
        ResponseEntity<List<UsuarioModel>> usuarios = usuarioFeignClient.listarUsuarios();

        HttpStatusCode httpStatusCode = usuarios.getStatusCode();

        if(httpStatusCode == HttpStatus.OK) 
        {
            List<UsuarioModel> usuariosLista = usuarios.getBody();
            return usuariosLista;
        } 
        else 
        {
            return new ArrayList<>();
        }
    }

}
