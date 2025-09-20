package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alfredgood.api_ciber.modelo.dto.create.UtilidadCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.UtilidadDTO;
import io.github.alfredgood.api_ciber.servicio.UtilidadServ;
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
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1")
public class UtilidadControlador {

    private final UtilidadServ servicio;

    public UtilidadControlador(UtilidadServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/utilidad")
    public ResponseEntity<UtilidadDTO> nuevaUtilidad(@Valid @RequestBody UtilidadCreateDTO create) {
        return new ResponseEntity<UtilidadDTO>(servicio.nuevo(create), HttpStatus.CREATED);
    }

    @GetMapping("/utilidad/{id}")
    public ResponseEntity<UtilidadDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok().body(servicio.buscarPorId(id));
    }
    
    @GetMapping("/utilidades")
    public ResponseEntity<List<UtilidadDTO>> listado(@RequestParam String param) {
        return ResponseEntity.ok().body(servicio.lista());
    }

    @DeleteMapping("/utilidad/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/utilidad/{id}/reabastecer")
    public ResponseEntity<UtilidadDTO> reabastecer(@PathVariable long id, @RequestParam int cantidad){
        return ResponseEntity.ok().body(servicio.reabastecer(id, cantidad));
    }
    
}