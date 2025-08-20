package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import io.github.alfredgood.api_ciber.modelo.Entitys.Cliente;
import io.github.alfredgood.api_ciber.modelo.Entitys.Prestamo;
import io.github.alfredgood.api_ciber.modelo.dto.create.PrestamoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PrestamoDTO;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "devuelto", ignore = true)
    @Mapping(target = "fechaPrestamo", ignore = true)
    @Mapping(target = "cliente", source = "clienteObtenido")
    Prestamo toEntity(PrestamoCreateDTO create, Cliente clienteObtenido);

    PrestamoDTO toDTO(Prestamo prestamo);

    List<PrestamoDTO> toListDTO(List<Prestamo> lista);
}
