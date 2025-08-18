package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UtilidadDTO(

    long id,

    String nombre,

    int stack,

    double precio,

    @JsonProperty("fecha_registro")
    String fechaRegistro
) {

}
