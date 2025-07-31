package io.github.alfredgood.api_ciber.modelo.dto.create;

import jakarta.validation.constraints.NotBlank;

public record PlataformaCreateDTO(
    @NotBlank(message = "Especifique el nombre de la plataforma no repetir") String nombre) {
}