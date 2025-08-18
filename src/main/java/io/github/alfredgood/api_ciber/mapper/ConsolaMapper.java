package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Consola;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.ConsolaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ConsolaDTO;

@Mapper(componentModel = "spring")
public interface ConsolaMapper {

    @Mapping(target = "disponible", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plataforma", source = "plataformaSeleccionada")
    Consola toEntity(ConsolaCreateDTO create, Plataforma plataformaSeleccionada);

    @Mapping(target = "plataforma",expression = "java(consola.getPlataforma()!=null? consola.getPlataforma().getNombre() : null)")
    ConsolaDTO toDTO (Consola consola);

    List<ConsolaDTO> toListDTO (List<Consola> consolas);
}
