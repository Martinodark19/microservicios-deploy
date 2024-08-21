package com.usuario.service.servicio.listeners;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.usuario.service.modelos.Carro;

@Configuration
public class UsuarioListener 
{
    @KafkaListener(topics = {"usuarioEventsResponse-topic"}, groupId = "grupoUser-id")
    public List<Carro> getCarros(int usuarioId) 
	{
		
		return verificarExistenciaCarro;

	}    
}
