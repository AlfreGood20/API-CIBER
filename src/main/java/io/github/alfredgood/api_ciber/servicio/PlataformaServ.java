package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.mapper.PlataformaMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PlataformaDTO;
import io.github.alfredgood.api_ciber.repositorio.PlataformaRepo;

@Service
public class PlataformaServ {

    private final PlataformaRepo repositorio;
    private final PlataformaMapper mapper;

    public PlataformaServ(PlataformaRepo repositorio, PlataformaMapper mapper){
        this.repositorio = repositorio;
        this.mapper=mapper;
    }

    public PlataformaDTO crear(PlataformaCreateDTO create){
        Plataforma plataforma=mapper.toEntity(create);
        repositorio.save(plataforma);
        return mapper.tDTO(plataforma);
    }

    public List<PlataformaDTO> listaPlataformas(){
        return mapper.toListDTO(repositorio.findAll());
    }
}
