package io.github.alfredgood.api_ciber.mapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.alfredgood.api_ciber.modelo.Entitys.Consola;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.ConsolaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ConsolaDTO;

@Component
public class ConsolaMapper {

    public Consola toEntidad(ConsolaCreateDTO create,Plataforma plataforma){
        return Consola.builder()
                .generacion(create.generacion())
                .precioRenta(create.precioRenta())
                .stack(create.stack())
                .plataforma(plataforma).build();
    }

    public ConsolaDTO aDto(Consola consola){
        return ConsolaDTO.builder()
                    .id(consola.getId())
                    .generacion(consola.getGeneracion())
                    .precioRenta(consola.getPrecioRenta())
                    .stack(consola.getStack())
                    .disponible(consola.isDisponible())
                    .plataforma(consola.getPlataforma().getNombre())
                    .build();
    }

    public List<ConsolaDTO> aListaDto(List<Consola> consolas){
        return consolas.stream()
        .map(c -> ConsolaDTO.builder()
                    .id(c.getId())
                    .generacion(c.getGeneracion())
                    .precioRenta(c.getPrecioRenta())
                    .stack(c.getStack())
                    .disponible(c.isDisponible())
                    .plataforma(c.getPlataforma().getNombre())
                    .build()
        ).collect(Collectors.toList());
    }
}