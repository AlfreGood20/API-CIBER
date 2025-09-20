package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.excepciones.SolicitudIncorrecta;
import io.github.alfredgood.api_ciber.mapper.UtilidadMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Utilidad;
import io.github.alfredgood.api_ciber.modelo.dto.create.UtilidadCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.UtilidadDTO;
import io.github.alfredgood.api_ciber.repositorio.UtilidadRepo;
import jakarta.transaction.Transactional;

@Service
public class UtilidadServ{

    private final UtilidadRepo utilidadRepo;
    private final UtilidadMapper mapper;

    public UtilidadServ(UtilidadRepo utilidadRepo, UtilidadMapper mapper) {
        this.utilidadRepo = utilidadRepo;
        this.mapper=mapper;
    }

    public UtilidadDTO nuevo(UtilidadCreateDTO create) {
        Utilidad utilidad=mapper.toEntity(create);
        utilidadRepo.save(utilidad);
        return mapper.toDTO(utilidad);
    }


    public UtilidadDTO buscarPorId(long id) {
        Utilidad utilidad=utilidadRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Utilidad id "+id+" no encontrado"));
        return mapper.toDTO(utilidad);
    }


    public List<UtilidadDTO> lista() {
        return mapper.toListDTO(utilidadRepo.findAll());
    }


    public void eliminarPorId(long id) {
        Utilidad utilidad=utilidadRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Utilidad id "+id+" no encontrado"));
        utilidadRepo.delete(utilidad);
    }

    @Transactional
    public UtilidadDTO reabastecer(long id, int cantidad){
        Utilidad utilidad=utilidadRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Utilidad "+id+" no encontrado"));

        if(cantidad<0){
            throw new SolicitudIncorrecta("No se puede ingresar la cantidad menor a 0");
        }

        cantidad+=utilidad.getStack();
        utilidad.setStack(cantidad);
        utilidadRepo.save(utilidad);
        return mapper.toDTO(utilidad);
    }
}