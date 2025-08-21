package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.PrestamoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PrestamoDTO;
import io.github.alfredgood.api_ciber.servicio.PrestamoServ;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1")
public class PrestamoControlador {

    private final PrestamoServ servicio;

    public PrestamoControlador(PrestamoServ servicio) {
        this.servicio = servicio;
    }
    

    @PostMapping("/prestamo")
    public ResponseEntity<PrestamoDTO> nuevoPrestamo(@Valid @RequestBody PrestamoCreateDTO create) {
        return new ResponseEntity<PrestamoDTO>(servicio.nuevo(create), HttpStatus.CREATED);
    }

    @GetMapping("/prestamos")
    public ResponseEntity<List<PrestamoDTO>> listaDePrestamos() {
        return ResponseEntity.ok().body(servicio.listaCompleta());
    }

    @PatchMapping("/prestamo/{id}/devolucion")
    public ResponseEntity<PrestamoDTO> devolverPrestamo (@PathVariable long id){
        return ResponseEntity.ok().body(servicio.devolver(id));
    }

    @DeleteMapping("/prestamo/{id}")
    public ResponseEntity<Void> eliminarPRestamoPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
    
}