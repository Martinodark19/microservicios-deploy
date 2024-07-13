package com.example.authentication_service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.authentication_service.feignClients.modelsFeignClients.CarroModel;
import com.example.authentication_service.feignClients.modelsFeignClients.MotoModel;
import com.example.authentication_service.feignClients.modelsFeignClients.UsuarioModel;

@FeignClient(name = "usuario-service")
public interface UsuarioFeignClient {
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios();

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> obtenerUsuario(@PathVariable("id") int id);

    @PostMapping
    public ResponseEntity<UsuarioModel> guardarUsuario(@RequestBody UsuarioModel usuario);

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<CarroModel>> getCarros(@PathVariable("usuarioId") int usuarioId);

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<CarroModel> saveCarro(@PathVariable("usuarioId") int usuarioId,
            @RequestBody CarroModel carro);

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<MotoModel>> listarMotos(@PathVariable("usuarioId") int usuarioId);

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<MotoModel> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody MotoModel moto);
}
