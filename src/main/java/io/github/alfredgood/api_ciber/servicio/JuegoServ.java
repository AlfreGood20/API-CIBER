package io.github.alfredgood.api_ciber.servicio;

import java.util.List;
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

    private final JuegoRepo juegoRepo;
    private final PlataformaRepo plataformaRepo;
    private final JuegoMapper juegoMapper;

    public JuegoServ(JuegoRepo juegoRepo, PlataformaRepo plataformaRepo, JuegoMapper juegoMapper) {
        this.juegoRepo = juegoRepo;
        this.plataformaRepo = plataformaRepo;
        this.juegoMapper=juegoMapper;
    }

    public JuegoCreateDTO crear(JuegoCreateDTO create) {
        Plataforma plataforma = plataformaRepo.findById(create.plataforma_id())
        .orElseThrow(() -> new RecursoNoEncontradoException("Plataforma con id " + create.plataforma_id() + " no encontrada"));

        juegoRepo.save(juegoMapper.aEntidad(create, plataforma));
        return create;
    }

    public List<JuegoDTO> lista() {
        return juegoMapper.aListaDto(juegoRepo.findAll());
    }

    public void eliminar(long id) {

        if (!juegoRepo.existsById(id)) {
            throw new RecursoNoEncontradoException("Juego con id " + id + " no encontrado");
        }

        juegoRepo.deleteById(id);
    }

    public JuegoDTO obtenerPorId(long id) {
        Juego encontrado = juegoRepo.findById(id).get();

        if (encontrado == null) {
            throw new RecursoNoEncontradoException("Juego con id " + id + " no encontrado");
        }

        JuegoDTO juego = juegoMapper.aDto(encontrado);

        return juego;
    }

}