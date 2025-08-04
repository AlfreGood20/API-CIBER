package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PlataformaDTO;

@Mapper(componentModel = "spring")
public interface PlataformaMapper {

    Plataforma toEntity(PlataformaCreateDTO create);

    PlataformaDTO tDTO(Plataforma plataforma);

    List<PlataformaDTO> toListDTO(List<Plataforma>plataformas);
}
