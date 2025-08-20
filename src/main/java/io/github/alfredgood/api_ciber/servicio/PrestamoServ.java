package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.PrestamoMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Cliente;
import io.github.alfredgood.api_ciber.modelo.Entitys.Prestamo;
import io.github.alfredgood.api_ciber.modelo.dto.create.PrestamoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PrestamoDTO;
import io.github.alfredgood.api_ciber.repositorio.ClienteRepo;
import io.github.alfredgood.api_ciber.repositorio.PrestamoRepo;

@Service
public class PrestamoServ {

    private final PrestamoRepo prestamoRepo;
    private final PrestamoMapper mapper;
    private final ClienteRepo clienteRepo;

    public PrestamoServ(PrestamoRepo prestamoRepo, PrestamoMapper mapper, ClienteRepo clienteRepo) {
        this.prestamoRepo = prestamoRepo;
        this.mapper=mapper;
        this.clienteRepo = clienteRepo;
    }
    

    public PrestamoDTO nuevo(PrestamoCreateDTO create){
        long clienteId= create.clienteId();
        Cliente cliente= clienteRepo.findById(clienteId)
            .orElseThrow(()-> new RecursoNoEncontradoException("Cliente id "+clienteId+" no encontrado"));

        Prestamo prestamo=mapper.toEntity(create, cliente);
        prestamoRepo.save(prestamo);

        return mapper.toDTO(prestamo);
    }


    public void eliminarPorId(long id){
        Prestamo prestamo = prestamoRepo.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException("prestamo id "+id+" no encontrado"));

        prestamoRepo.delete(prestamo);
    }

    public List<PrestamoDTO> listaCompleta(){
        return mapper.toListDTO(prestamoRepo.findAll());
    }

}