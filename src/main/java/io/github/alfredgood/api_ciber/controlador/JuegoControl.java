package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.JuegoCreateDTO;
import io.github.alfredgood.api_ciber.servicio.JuegoServ;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class JuegoControl {

    private JuegoServ juegoServ;

    public JuegoControl(JuegoServ juegoServ){
        this.juegoServ=juegoServ;
    }

    @PostMapping("/juego")
    public ResponseEntity<?> nuevo(@Valid @RequestBody JuegoCreateDTO create) {
        juegoServ.crear(create);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }

    @DeleteMapping("/juego/{id}/eliminar")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        juegoServ.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/juegos")
    public ResponseEntity<?> mostrarLista() {
        return ResponseEntity.ok().body(juegoServ.lista());
    }

    @GetMapping("/juego/{id}/buscar")
    public ResponseEntity<?> obtenerPorId(@PathVariable long id) {
        return ResponseEntity.ok().body(juegoServ.obtenerPorId(id));
    }
    
}