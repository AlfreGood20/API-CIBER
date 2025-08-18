package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JuegoDTO(

    long id,

    String titulo,

    int stack,

    @JsonProperty("precio_venta")
    double precioVenta,

    @JsonProperty("precio_renta")
    double precioRenta,

    boolean vendible,

    boolean disponible,

    String plataforma
) {
}