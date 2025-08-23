package io.github.alfredgood.api_ciber.modelo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VentaDTO(

    long id,

    @JsonProperty("producto_id")
    long productoId,

    @JsonProperty("tipo_producto")
    String tipoProducto,

    @JsonProperty("fecha_registro")
    String fechaRegistro,

    String estado,

    @JsonProperty("tipo_pago")
    String tipoPago,

    @JsonProperty("codigo_venta")
    String codigoVenta,

    int cantidad,

    @JsonProperty("precio_unitario")
    double precioUnitario,

    double total

) {

}
