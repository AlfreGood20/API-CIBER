package io.github.alfredgood.api_ciber.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.modelo.Entitys.Plataforma;
import io.github.alfredgood.api_ciber.modelo.dto.create.PlataformaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PlataformaDTO;
import io.github.alfredgood.api_ciber.repositorio.PlataformaRepo;

@Service
public class PlataformaServ {

    private final PlataformaRepo plataformaRepo;

    public PlataformaServ(PlataformaRepo plataformaRepo) {
        this.plataformaRepo = plataformaRepo;
    }

    public PlataformaCreateDTO crear(PlataformaCreateDTO create) {
        Plataforma plataforma = Plataforma.builder().nombre(create.nombre()).build();
        
        plataformaRepo.save(plataforma);
        return create;
    }

    public List<PlataformaDTO> lista() {
        return plataformaRepo.findAll().stream()
                .map(p -> PlataformaDTO.builder()
                        .id(p.getId())
                        .nombre(p.getNombre()).build())
                .collect(Collectors.toList());
    }

    public void eliminar(long id) {
        
        if (!plataformaRepo.existsById(id)) {
            throw new RecursoNoEncontradoException("Plataforma id "+id+ "no encontrado");
        }

        plataformaRepo.deleteById(id);
    }

    public PlataformaDTO obtenerPorId(long id){
        if (!plataformaRepo.existsById(id)) {
            throw new RecursoNoEncontradoException("Plataforma id "+id+ "no encontrado");
        }

        Plataforma plataformaEncontrado=plataformaRepo.findById(id).get();

        return PlataformaDTO.builder()
                    .id(plataformaEncontrado.getId())
                    .nombre(plataformaEncontrado.getNombre()).build();
    }
}