package io.github.alfredgood.api_ciber.modelo.dto.response;

import lombok.Builder;

@Builder
public record ConsolaDTO(

    long id,
    String generacion,
    double precioRenta,
    int stack,
    boolean disponible,
    String plataforma
) {
}