package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsolaDTO(

    long id,

    String generacion,

    @JsonProperty("precio_renta")
    double precioRenta,

    int stack,

    boolean disponible,
    
    String plataforma
) {
}