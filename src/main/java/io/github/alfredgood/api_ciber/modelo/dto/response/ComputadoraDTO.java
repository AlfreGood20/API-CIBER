package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ComputadoraDTO(

    long id,

    String marca,

    String procesador,

    @JsonProperty("cantidad_ram")
    String cantidadDeRam,

    @JsonProperty("tarjeta_video")
    String tarjetaDeVideo,

    boolean disponible
) {
}