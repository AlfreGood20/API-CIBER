package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PrestamoDTO(

    long id,

    @JsonProperty("fecha_prestamo")
    String fechaPrestamo,

    @JsonProperty("fecha_devolver")
    String fechaDevolver,
    
    @JsonProperty("tipo")
    String tipoProducto,

    boolean devuelto,

    ClienteDTO cliente
) {
}