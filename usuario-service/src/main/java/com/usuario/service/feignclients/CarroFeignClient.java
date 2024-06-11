package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Carro;

@FeignClient(name = "carro-service")
@RequestMapping("/carro")
public interface CarroFeignClient 
{

	@PostMapping()
	public Carro guardarCarro(@RequestBody Carro carro);

	@GetMapping("{id}")
	public List<Carro> obtenerCarro(@PathVariable("usuarioId") int id);

	@GetMapping("/usuario/{usuarioId}")
	public List<Carro> listarCarrosPorUsuarioId(@PathVariable("usuarioId") int usuarioId);

	@GetMapping("/buscarIdUsuario/{usuarioId}")
	public List<Carro> buscarIdParaUsuario(@PathVariable("usuarioId") int usuarioId);

}
