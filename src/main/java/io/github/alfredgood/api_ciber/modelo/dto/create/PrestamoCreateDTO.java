package io.github.alfredgood.api_ciber.modelo.dto.create;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.alfredgood.api_ciber.modelo.enumerado.TipoProducto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrestamoCreateDTO(

    @NotNull(message = "Fecha devolver es obligatoria")
    @JsonProperty("fecha_devolver")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaDevolver,

    @NotNull(message = "El tipo de producto es obligatorio")
    @JsonProperty("tipo")
    TipoProducto tipoProducto,

    @Min(value = 1, message = "El id del cliente no puede ser menor a 1")
    @JsonProperty("cliente_id")
    long clienteId,

    @Min(value = 1, message = "El id del producto no puede ser menor a 1")
    @JsonProperty("producto_id")
    long productoId
) {

}
