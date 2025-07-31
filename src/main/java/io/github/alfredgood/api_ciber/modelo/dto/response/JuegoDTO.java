package io.github.alfredgood.api_ciber.modelo.dto.response;

import lombok.Builder;

@Builder
public record JuegoDTO(
    long id,
    String titulo,
    int stack,
    double precioVenta,
    double precioRenta,
    boolean vendible,
    boolean disponible,
    String plataforma
) {
}