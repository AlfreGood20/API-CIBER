package io.github.alfredgood.api_ciber.modelo.dto.create;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;

public record JuegoCreateDTO(

@NotBlank(message = "Obligatorio el titulo")
    String titulo,

@Min(value = 0, message = "El stack debe ser mayor o igual a 0")
    int stack,

@DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser mayor a 0")
@JoinColumn(name = "precio_venta")
    double precioVenta,

    boolean vendible,

@Min(value = 1, message = "El ID de la plataforma debe ser mayor a 0")
    long plataforma_id
) {
}