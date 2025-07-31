package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.servicio.PlataformaServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class PlataformaControl {

    private final PlataformaServ plataformaServ;

    public PlataformaControl(PlataformaServ plataformaServ) {
        this.plataformaServ = plataformaServ;
    }

    @PostMapping("/plataforma")
    public ResponseEntity<?> nuevo(@RequestBody PlataformaCreateDTO create) {
        plataformaServ.crear(create);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }

    @GetMapping("/plataformas")
    public ResponseEntity<?> mostrarLista() {
        return ResponseEntity.ok().body(plataformaServ.lista());
    }

    @GetMapping("/plataforma/{id}/buscar")
    public ResponseEntity<?> obtenerPorId(@PathVariable long id) {
        return ResponseEntity.ok().body(plataformaServ.obtenerPorId(id));
    }
    
}