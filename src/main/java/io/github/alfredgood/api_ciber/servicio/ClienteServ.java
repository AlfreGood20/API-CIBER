package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.ClienteMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Cliente;
import io.github.alfredgood.api_ciber.modelo.dto.create.ClienteCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ClienteDTO;
import io.github.alfredgood.api_ciber.repositorio.ClienteRepo;

@Service
public class ClienteServ {

    private final ClienteRepo repositorio;
    private final ClienteMapper mapper;

    public ClienteServ(ClienteRepo repositorio, ClienteMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    public ClienteDTO crear(ClienteCreateDTO create){
        return mapper.toDTO(repositorio.save(mapper.toEntity(create)));
    }

    public List<ClienteDTO> listaClientes(){
        return mapper.toListDTO(repositorio.findAll());
    }

    public void eliminarPorId(long id){
        Cliente encontrado=repositorio.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Cliente id "+id+" no encontrado"));
        repositorio.delete(encontrado);
    }
}