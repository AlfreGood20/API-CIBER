package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Venta;
import io.github.alfredgood.api_ciber.modelo.dto.create.VentaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.VentaDTO;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigoVenta", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Venta toEntity(VentaCreateDTO create, double precioUnitario);

    VentaDTO tDTO(Venta venta);

    List<VentaDTO> toListDTO(List<Venta> lista);
}
