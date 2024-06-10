package com.usuario.service.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Boolean getUsuarioById(int id) {
		Optional<Usuario> usuarioBuscar = usuarioRepository.findById(id);
		System.out.println(usuarioBuscar);
		if (usuarioBuscar.isPresent()) {
			System.out.println("paso por el servicio y el usuario se encontro");
			return true;
		} else {
			System.out.println("no se encontro el usuario");
			return false;
		}
	}

	public Usuario getUsuarioObject(int id) {
		Optional<Usuario> obtenerUsuarioPorId = usuarioRepository.findById(id);
		Usuario usuarioObtenido = obtenerUsuarioPorId.get();

		return usuarioObtenido;
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

	public List<Carro> getCarros(int usuarioId) {
		List<Carro> verificarExistenciaCarro = carroFeignClient.listarCarrosPorUsuarioId(usuarioId);
		System.out.println(verificarExistenciaCarro);

		return verificarExistenciaCarro;

	}

	// tenemos que arreglar quedamos en motos
	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = new ArrayList<>();

		try {
			motos = motoFeignClient.listarMotosPorUsuarioId(usuarioId);
			System.out.println("paso por aqui tambien");
		}

		catch (Exception e) {
			System.out.println("Error al llamar a motoFeignClient.listarMotosPorUsuarioId: " + e.getMessage());
		}

		return motos;
	}

	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.guardarCarro(carro);
		
		return nuevoCarro;
	}

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.guardarMoto(moto);
		return nuevaMoto;
	}

	

}
