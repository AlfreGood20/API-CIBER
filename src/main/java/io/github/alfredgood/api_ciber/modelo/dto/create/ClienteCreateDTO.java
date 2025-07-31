package io.github.alfredgood.api_ciber.modelo.dto.create;

import jakarta.validation.constraints.*;

public record ClienteCreateDTO(

@NotBlank(message = "Obligatorio el nombre")
    String nombre,

@NotBlank(message = "Obligatorio los apellidos")
    String apellidos,

@NotBlank(message = "Obligatorio el numero telefonico")
@Size(min = 7, max = 10, message = "Número telefónico debe tener entre 7 y 10 dígitos")
    String numeroTelefonico,
    
@NotBlank(message = "Obligatorio la direccion")
    String direccion
) {
}