package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public record ClienteDTO(

    long id,
    
    String nombre,

    String apellidos,

    @JsonProperty("numero_telefonico")
    String numeroTelefonico,

    String direccion,

    @JsonProperty("fecha_registro")
    String fechaRegistro
) {

}
