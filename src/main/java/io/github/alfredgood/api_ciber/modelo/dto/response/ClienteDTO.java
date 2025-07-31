package io.github.alfredgood.api_ciber.modelo.dto.response;


public record ClienteDTO(

    long id,
    
    String nombre,

    String apellidos,

    String numeroTelefonico,

    String direccion
) {

}
