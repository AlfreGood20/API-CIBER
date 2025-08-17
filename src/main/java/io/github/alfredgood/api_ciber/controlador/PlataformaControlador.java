package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PlataformaDTO;
import io.github.alfredgood.api_ciber.servicio.PlataformaServ;
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
public class PlataformaControlador {

    private final PlataformaServ servicio;

    public PlataformaControlador(PlataformaServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/plataforma")
    public ResponseEntity<PlataformaDTO> nuevo(@RequestBody PlataformaCreateDTO create) {
        return new ResponseEntity<PlataformaDTO>(servicio.crear(create),HttpStatus.CREATED);
    }

    @GetMapping("/plataformas")
    public ResponseEntity<List<PlataformaDTO>> obtenerTodos() {
        return ResponseEntity.ok().body(servicio.listaPlataformas());
    }

    @DeleteMapping("/plataforma/{id}")
    public ResponseEntity<?>eliminarPorId(@PathVariable long id){
        return ResponseEntity.noContent().build();
    }

}