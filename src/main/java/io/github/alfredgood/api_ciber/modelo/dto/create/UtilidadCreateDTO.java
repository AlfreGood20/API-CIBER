package io.github.alfredgood.api_ciber.modelo.dto.create;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UtilidadCreateDTO(

    @NotBlank(message = "Es obligatorio el nombre de la utilidad")
    String nombre,

    @Min(value = 0,message = "El valor del stack no puede ser menor a 0")
    int stack,

    @DecimalMin(value = "0.0",message = "El precio no puede ser menor a 0")
    double precio
) {

}
