package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alfredgood.api_ciber.modelo.dto.create.UtilidadCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.UtilidadDTO;
import io.github.alfredgood.api_ciber.servicio.UtilidadServ;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}