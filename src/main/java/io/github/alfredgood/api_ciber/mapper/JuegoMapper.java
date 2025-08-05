package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Juego;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.JuegoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.JuegoDTO;


@Mapper(componentModel = "spring")
public interface JuegoMapper {

    @Mapping(target = "plataforma",source = "plataformaSeleccionada")
    @Mapping(target = "precioRenta",ignore = true)
    @Mapping(target = "disponible",ignore = true)
    @Mapping(target = "id",ignore = true)
    Juego toEntity(JuegoCreateDTO creacion, Plataforma plataformaSeleccionada);

    @Mapping(target = "plataforma",expression = "java(juego.getPlataforma()!=null? juego.getPlataforma().getNombre() : null)")
    JuegoDTO toDTO(Juego juego);

    List<JuegoDTO> toListDTO(List<Juego> juegos);

}