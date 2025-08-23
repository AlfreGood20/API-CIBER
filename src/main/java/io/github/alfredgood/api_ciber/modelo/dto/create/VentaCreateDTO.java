package io.github.alfredgood.api_ciber.modelo.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.alfredgood.api_ciber.modelo.enumerado.TipoDePago;
import io.github.alfredgood.api_ciber.modelo.enumerado.TipoProducto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VentaCreateDTO(

    @NotNull(message = "El id del producto es obligatorio")
    @JsonProperty("producto_id")
    long productoId,

    @NotNull(message = "El tipo de producto es obligatorio")
    @JsonProperty("tipo_producto")
    TipoProducto tipoProducto,

    @NotNull(message = "El tipo de pago es obligatorio")
    @JsonProperty("tipo_pago")
    TipoDePago tipoPago,

    @Min(value = 1,message = "El valor de la cantidad tiene que ser mayor a 1")
    int cantidad
) {

}
