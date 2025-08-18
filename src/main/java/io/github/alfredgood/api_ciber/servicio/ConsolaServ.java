package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.ConsolaMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Consola;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.ConsolaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ConsolaDTO;
import io.github.alfredgood.api_ciber.repositorio.ConsolaRepo;
import io.github.alfredgood.api_ciber.repositorio.PlataformaRepo;

@Service
public class ConsolaServ{

    private final ConsolaRepo consolaRepo;

    private final ConsolaMapper mapper;

    private final PlataformaRepo plataformaRepo;

    public ConsolaServ(ConsolaRepo consolaRepo, ConsolaMapper mapper, PlataformaRepo plataformaRepo) {
        this.consolaRepo = consolaRepo;
        this.mapper = mapper;
        this.plataformaRepo = plataformaRepo;
    }


    public ConsolaDTO crear(ConsolaCreateDTO create) {
        Plataforma plataforma=plataformaRepo.findById(create.plataforma_id()).orElseThrow(()-> new RecursoNoEncontradoException("Plataforma id "+create.plataforma_id()+" no encontrada"));
        Consola consola=mapper.toEntity(create, plataforma);
        consolaRepo.save(consola);
        return mapper.toDTO(consola);
    }


    public ConsolaDTO buscarPorId(long id) {
        Consola consola=consolaRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Consola id "+ id+" no encontrada"));
        return mapper.toDTO(consola);
    }


    public List<ConsolaDTO> lista() {
        return mapper.toListDTO(consolaRepo.findAll());
    }


    public void eliminarPorId(long id) {
        Consola consola=consolaRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Consola id "+ id+" no encontrada"));
        consolaRepo.delete(consola);
    }

}
