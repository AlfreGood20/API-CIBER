package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Computadora;
import io.github.alfredgood.api_ciber.modelo.dto.create.ComputadorasCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ComputadoraDTO;

@Mapper(componentModel = "spring")
public interface ComputadoraMapper {

    @Mapping(target = "disponible", ignore= true)
    @Mapping(target = "id", ignore = true)
    Computadora toEntity (ComputadorasCreateDTO create);

    @Mapping(target = "disponible", source = "disponible")
    ComputadoraDTO toDTO (Computadora entidad);

    List<ComputadoraDTO> toListDTO(List<Computadora> lista);
}
