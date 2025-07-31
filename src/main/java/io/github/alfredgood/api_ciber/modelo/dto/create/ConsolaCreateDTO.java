package io.github.alfredgood.api_ciber.modelo.dto.create;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record ConsolaCreateDTO(

    String generacion,

@DecimalMin(value = "0.0",message = "El valor debe ser mayor a 0")
    double precioRenta,

@Min(value = 0,message = "El valor tiene que ser mayor a 0")
    int stack,

@Min(value = 1, message = "El ID de la plataforma debe ser mayor a 0")
    long plataforma_id
) {
}