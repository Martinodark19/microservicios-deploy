package com.moto.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicios.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController 
{

	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos()
	{
		List<Moto> motos = motoService.getAll();
		if(motos.isEmpty()) 
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			System.out.println("deberia retornar motos");
			return ResponseEntity.ok(motos);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id){
		Moto moto = motoService.getMotoById(id);
		if(moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto)
	{
		System.out.println("llego al controlador moto en el metodo guardarMoto");
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId)
	{
		System.out.println("llego al controlador de moto");
		List<Moto> motos = motoService.byUsuarioId(usuarioId);
		
		if(motos.isEmpty()) 
		{
			System.out.println("las motos estan vacias, desde controlador motoController, metodo listarMotosPorUsuarioId");
			return ResponseEntity.noContent().build();
		}
		else
		{
			System.out.println("se retornaron las motos correctamente y deberias ver las listas, este es el metodo de listarMotosPorUsuarioId del motoController ");
			return ResponseEntity.ok(motos);

		}
	}
	
}
