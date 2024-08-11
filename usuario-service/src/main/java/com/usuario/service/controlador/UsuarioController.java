package com.usuario.service.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/usuario")
public class UsuarioController 
{

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() 
	{
		System.out.println("llego a listar usuarios desde gatewlly");
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.getUsuarioObject(id);
		if (usuario == null) 
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(usuario);

		}
	}	

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int usuarioId) 
	{

		System.out.println("LLEGO AL USUARIO DESDE GATEWAY");
		List<Carro> confirmarCarrosExist = usuarioService.getCarros(usuarioId);
		if(confirmarCarrosExist.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			return ResponseEntity.ok(confirmarCarrosExist);	
		}
	}


	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> saveCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro) 
	{
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}


	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int usuarioId)
	{

		List<Moto> motos = usuarioService.getMotos(usuarioId);

		if(motos.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			return ResponseEntity.ok(motos);
		}

	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMoto")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) 
	{

		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);

		return ResponseEntity.ok(nuevaMoto);
	}

	

	
	// Fallbacks methods
	public ResponseEntity<String> fallBackGetCarros(RuntimeException exception) 
	{
		return ResponseEntity.internalServerError().body("Ha ocurrido un error inesperado al intentar obtener los carros");
	}

	public ResponseEntity<String> fallBackGetMotos(RuntimeException exception) 
	{
		return ResponseEntity.internalServerError().body("Ha ocurrido un error inesperado al intentar obtener las motos");
	}

	public ResponseEntity<String> fallBackSaveCarro(RuntimeException exception) 
	{
		return ResponseEntity.internalServerError().body("Ha ocurrido un error inesperado al guardar los carros");
	}

	public ResponseEntity<List<Moto>> fallBackSaveMoto(RuntimeException exception) 
	{
		List<Moto> motoVacia = Collections.emptyList();
		return ResponseEntity.internalServerError().body(motoVacia);
	}

}