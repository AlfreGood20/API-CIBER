package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.JuegoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.JuegoDTO;
import io.github.alfredgood.api_ciber.servicio.JuegoServ;
import jakarta.validation.Valid;
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
public class JuegoControlador {

    private final JuegoServ servicio;

    public JuegoControlador(JuegoServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/juego")
    public ResponseEntity<JuegoDTO> nuevo(@Valid @RequestBody JuegoCreateDTO creacion) {
        return new ResponseEntity<JuegoDTO>(servicio.crear(creacion), HttpStatus.CREATED);
    }

    @GetMapping("/juego/{id}")
    public ResponseEntity<JuegoDTO> buscarPorId(@PathVariable  long id) {
        return ResponseEntity.ok().body(servicio.obtenerPorId(id));
    }

    @GetMapping("/juegos")
    public ResponseEntity<List<JuegoDTO>> obtenerTodos() {
        return ResponseEntity.ok().body(servicio.listaJuegos());
    }

    @DeleteMapping("/juego/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/juegos/plataforma/{id}")
    public ResponseEntity<List<JuegoDTO>> juegosPorPlataforma(@PathVariable long id) {
        return ResponseEntity.ok().body(servicio.obtenerPorIdPlataforma(id));
    }
    
}