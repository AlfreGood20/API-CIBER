package io.github.alfredgood.api_ciber.servicio;

import java.util.List;
import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.ConsolaMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.ConsolaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.ConsolaDTO;
import io.github.alfredgood.api_ciber.repositorio.ConsolaRepo;
import io.github.alfredgood.api_ciber.repositorio.PlataformaRepo;

@Service
public class ConsolaServ {

    public final ConsolaRepo consolaRepo;
    private final PlataformaRepo plataformaRepo;
    private final ConsolaMapper consolaMapper;

    public ConsolaServ(ConsolaRepo consolaRepo, PlataformaRepo plataformaRepo, ConsolaMapper consolaMapper) {
        this.consolaRepo = consolaRepo;
        this.plataformaRepo = plataformaRepo;
        this.consolaMapper=consolaMapper;
    }

    public ConsolaCreateDTO crear(ConsolaCreateDTO create){
        Plataforma plataforma=plataformaRepo.findById(create.plataforma_id())
        .orElseThrow(()-> new RecursoNoEncontradoException("Plataforma con id " + create.plataforma_id() + " no encontrada") );

        consolaRepo.save(consolaMapper.toEntidad(create,plataforma));
        return create;
    }

    public List<ConsolaDTO> lista(){
        return consolaMapper.aListaDto(consolaRepo.findAll());
    }

    public void eliminar(long id){
        if(!consolaRepo.existsById(id)){
            throw new RecursoNoEncontradoException("Consola a eliminar no encontrado");
        }

        consolaRepo.deleteById(id);
    }

}
