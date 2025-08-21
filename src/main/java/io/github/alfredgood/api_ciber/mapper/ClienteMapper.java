package io.github.alfredgood.api_ciber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.alfredgood.api_ciber.modelo.Entitys.Cliente;
import io.github.alfredgood.api_ciber.modelo.dto.create.ClienteCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "prestamos", ignore = true)
    Cliente toEntity(ClienteCreateDTO create);

    ClienteDTO toDTO(Cliente cliente);

    List<ClienteDTO> toListDTO(List<Cliente> clientes);
}
