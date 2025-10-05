package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.alfredgood.api_ciber.modelo.dto.create.VentaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.VentaDTO;
import io.github.alfredgood.api_ciber.modelo.enumerado.EstadoVenta;
import io.github.alfredgood.api_ciber.servicio.VentaServ;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Venta", description = "Acciones que se pueden hacer en venta")
public class VentaControlador {

    private final VentaServ servicio;

    public VentaControlador(VentaServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/venta")
    public ResponseEntity<VentaDTO> nuevaVenta(@Valid @RequestBody VentaCreateDTO create) {
        return new ResponseEntity<VentaDTO>(servicio.nuevo(create),HttpStatus.CREATED);
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<VentaDTO>> todasVentas() {
        return ResponseEntity.ok().body(servicio.listaTodos());
    }

    @DeleteMapping("/venta/{id}")
    public ResponseEntity<Void> eliminarVentaPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/venta/{id}/")
    public ResponseEntity<VentaDTO> actualizarEstadoVenta(@PathVariable long id, @RequestParam EstadoVenta estado){
        return ResponseEntity.ok().body(servicio.actualizarEstado(id, estado));
    }

    @GetMapping("/ventas/canceladas")
    public ResponseEntity<List<VentaDTO>> ventasCanceladas() {
        return ResponseEntity.ok().body(servicio.listaCancelados());
    }

    @GetMapping("/ventas/completadas")
    public ResponseEntity<List<VentaDTO>> ventasCompletadas() {
        return ResponseEntity.ok().body(servicio.listaCompletadas());
    }

    @GetMapping("/ventas/pendientes")
    public ResponseEntity<List<VentaDTO>> ventasPendientes() {
        return ResponseEntity.ok().body(servicio.listaPendientes());
    }
    
}
