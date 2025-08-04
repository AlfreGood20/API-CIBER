package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PlataformaDTO;

@Mapper(componentModel = "spring")
public interface PlataformaMapper {

    @Mapping(target = "id", ignore = true)
    Plataforma toEntity(PlataformaCreateDTO create);

    PlataformaDTO tDTO(Plataforma plataforma);

    List<PlataformaDTO> toListDTO(List<Plataforma>plataformas);
}
