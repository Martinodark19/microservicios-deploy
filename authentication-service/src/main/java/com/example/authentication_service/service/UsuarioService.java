package com.example.authentication_service.service;

import java.util.ArrayList;
import java.util.List;

import com.example.authentication_service.feignClients.UsuarioFeignClient;
import com.example.authentication_service.feignClients.modelsFeignClients.UsuarioModel;

public class UsuarioService 
{
    private UsuarioFeignClient usuarioFeignClient;

    public UsuarioService(UsuarioFeignClient usuarioFeignClient)
    {
        this.usuarioFeignClient = usuarioFeignClient;
    }

    public List<UsuarioModel> obtenerUsuarios()
    {
        return usuarioFeignClient.listarUsuarios();
    }

    


}
