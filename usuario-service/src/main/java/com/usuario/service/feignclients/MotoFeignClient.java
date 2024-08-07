package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Moto;

@FeignClient(name = "moto-service")
@RequestMapping("/moto")
public interface MotoFeignClient 
{

	@PostMapping()
	public Moto guardarMoto(@RequestBody Moto moto);

	@GetMapping("/usuario/{usuarioId}")
	public List<Moto> listarMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId);

	
}











