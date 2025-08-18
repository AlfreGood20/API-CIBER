package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Utilidad;
import io.github.alfredgood.api_ciber.modelo.dto.create.UtilidadCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.UtilidadDTO;

@Mapper(componentModel = "spring")
public interface UtilidadMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Utilidad toEntity (UtilidadCreateDTO create);

    UtilidadDTO toDTO (Utilidad entity);

    List<UtilidadDTO> toListDTO(List<Utilidad> lista);
}
