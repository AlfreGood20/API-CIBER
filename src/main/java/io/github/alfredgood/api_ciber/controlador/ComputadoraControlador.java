package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alfredgood.api_ciber.modelo.dto.create.ComputadorasCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ComputadoraDTO;
import io.github.alfredgood.api_ciber.servicio.ComputadoraServ;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1")
public class ComputadoraControlador {

    private final ComputadoraServ servicio;

    public ComputadoraControlador(ComputadoraServ servicio) {
        this.servicio=servicio;
    }
    
    @PostMapping("/computadora")
    public ResponseEntity<ComputadoraDTO> nuevo(@Valid @RequestBody ComputadorasCreateDTO create) {
        return new ResponseEntity<ComputadoraDTO>(servicio.crear(create), HttpStatus.CREATED);
    }

    @GetMapping("/computadoras")
    public ResponseEntity<List<ComputadoraDTO>> obtenerListado(@RequestParam String param) {
        return ResponseEntity.ok().body(servicio.lista());
    }
    
    @DeleteMapping("/computadora/{id}")
    public ResponseEntity<?> eliminarPorId (@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
