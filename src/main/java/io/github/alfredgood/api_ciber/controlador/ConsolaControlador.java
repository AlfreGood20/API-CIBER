package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alfredgood.api_ciber.modelo.dto.create.ConsolaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ConsolaDTO;
import io.github.alfredgood.api_ciber.servicio.ConsolaServ;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Consola")
public class ConsolaControlador {

    private final ConsolaServ servicio;

    public ConsolaControlador(ConsolaServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/consola")
    public ResponseEntity<ConsolaDTO> nuevo(@RequestBody ConsolaCreateDTO create) {
        return new ResponseEntity<ConsolaDTO>(servicio.crear(create), HttpStatus.CREATED);
    }

    @GetMapping("/consolas")
    public ResponseEntity<List<ConsolaDTO>> obtenerListado() {
        return ResponseEntity.ok().body(servicio.lista());
    }

    @DeleteMapping("/consola/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable long id){
        return ResponseEntity.noContent().build();
    }       
}