package io.github.alfredgood.api_ciber.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.alfredgood.api_ciber.modelo.Entitys.Juego;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.JuegoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.JuegoDTO;

@Component
public class JuegoMapper {

    public Juego aEntidad(JuegoCreateDTO create, Plataforma plataforma){

        return Juego.builder().titulo(create.titulo())
                .stack(create.stack())
                .precioVenta(create.precioVenta())
                .vendible(create.vendible())
                .plataforma(plataforma).build();
    }

    public JuegoDTO aDto(Juego encontrado){
        return JuegoDTO.builder()
                .id(encontrado.getId())
                .titulo(encontrado.getTitulo())
                .stack(encontrado.getStack())
                .precioVenta(encontrado.getPrecioVenta())
                .precioRenta(encontrado.getPrecioRenta())
                .vendible(encontrado.isVendible())
                .disponible(encontrado.isDisponible())
                .plataforma(encontrado.getPlataforma().getNombre()).build();
    }

    public List<JuegoDTO> aListaDto(List<Juego> juegos){
        return juegos.stream()
                .map(j -> JuegoDTO.builder()
                        .id(j.getId())
                        .titulo(j.getTitulo())
                        .stack(j.getStack())
                        .precioVenta(j.getPrecioVenta())
                        .precioRenta(j.getPrecioRenta())
                        .vendible(j.isVendible())
                        .disponible(j.isDisponible())
                        .plataforma(j.getPlataforma().getNombre()).build())
                .collect(Collectors.toList());
    }
}