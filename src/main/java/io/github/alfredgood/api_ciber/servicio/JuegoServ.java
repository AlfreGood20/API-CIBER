package io.github.alfredgood.api_ciber.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.JuegoMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Juego;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.JuegoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.JuegoDTO;
import io.github.alfredgood.api_ciber.repositorio.JuegoRepo;
import io.github.alfredgood.api_ciber.repositorio.PlataformaRepo;

@Service
public class JuegoServ {

    public final JuegoRepo juegoRepo;
    public final PlataformaRepo plataformaRepo;
    public final JuegoMapper mapper;

    public JuegoServ(JuegoRepo juegoRepo, PlataformaRepo plataformaRepo, JuegoMapper mapper) {
        this.juegoRepo = juegoRepo;
        this.plataformaRepo = plataformaRepo;
        this.mapper = mapper;
    }

    public JuegoDTO crear(JuegoCreateDTO creacion){
        Plataforma plataforma=plataformaRepo.findById(creacion.plataforma_id()).orElseThrow(()->new RecursoNoEncontradoException("Plataforma id "+creacion.plataforma_id()+" no encontrado"));
        Juego juego=mapper.toEntity(creacion, plataforma);
        juegoRepo.save(juego);
        return mapper.toDTO(juego);
    }

    public JuegoDTO obtenerPorId(long id){
        Juego encontrado=juegoRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Juego id "+id+" no encontrado"));
        return mapper.toDTO(encontrado);
    }

    public List<JuegoDTO> listaJuegos(){
        return mapper.toListDTO(juegoRepo.findAll());
    }

    public void eliminarPorId(long id){
        Juego encontrado=juegoRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Juego id "+id+" no encontrado")); 
        juegoRepo.delete(encontrado);
    }

    public List<JuegoDTO>obtenerPorIdPlataforma(long id){

        if(!plataformaRepo.existsById(id)){
            throw new RecursoNoEncontradoException("Plataforma id " + id + " no encontrado");
        }

        return plataformaRepo.findById(id)
            .stream()
            .flatMap(p -> p.getJuegos().stream())
            .map(j -> mapper.toDTO(j))
            .collect(Collectors.toList());
    }
}