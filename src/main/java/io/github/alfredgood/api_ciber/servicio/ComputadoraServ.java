package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.ComputadoraMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Computadora;
import io.github.alfredgood.api_ciber.modelo.dto.create.ComputadorasCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ComputadoraDTO;
import io.github.alfredgood.api_ciber.repositorio.ComputadoraRepo;

@Service
public class ComputadoraServ {

    private final ComputadoraRepo computadoraRepo;

    private final ComputadoraMapper mapper;

    public ComputadoraServ(ComputadoraRepo computadoraRepo, ComputadoraMapper mapper) {
        this.computadoraRepo = computadoraRepo;
        this.mapper = mapper;
    }

  
    public ComputadoraDTO crear(ComputadorasCreateDTO create) {
        Computadora computadora = mapper.toEntity(create);
        computadoraRepo.save(computadora);
        return mapper.toDTO(computadora);
    }


    public ComputadoraDTO buscarPorId(long id) {
        Computadora computadora=computadoraRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Computadora id "+id+" no encontrada"));
        return mapper.toDTO(computadora);
    }


    public List<ComputadoraDTO> lista() {
        return mapper.toListDTO(computadoraRepo.findAll());
    }


    public void eliminarPorId(long id) {
        Computadora computadora=computadoraRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Computadora id "+id+" no encontrada"));
        computadoraRepo.delete(computadora);
    }

}
