package io.github.alfredgood.api_ciber.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.alfredgood.api_ciber.modelo.dto.create.ClienteCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ClienteDTO;
import io.github.alfredgood.api_ciber.servicio.ClienteServ;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class ClienteControlador {

    private final ClienteServ clienteServicio;

    public ClienteControlador(ClienteServ clienteServicio){
        this.clienteServicio=clienteServicio;
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> nuevo(@RequestBody ClienteCreateDTO create) {
        return new ResponseEntity<ClienteDTO>(clienteServicio.crear(create),HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listado() {
        return ResponseEntity.ok().body(clienteServicio.listaClientes());
    }  
}