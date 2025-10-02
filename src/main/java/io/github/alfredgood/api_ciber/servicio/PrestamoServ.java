package io.github.alfredgood.api_ciber.servicio;

import java.util.List;
import org.springframework.stereotype.Service;
import io.github.alfredgood.api_ciber.excepciones.RecursoNoDisponible;
import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.PrestamoMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Cliente;
import io.github.alfredgood.api_ciber.modelo.Entitys.Computadora;
import io.github.alfredgood.api_ciber.modelo.Entitys.Consola;
import io.github.alfredgood.api_ciber.modelo.Entitys.Juego;
import io.github.alfredgood.api_ciber.modelo.Entitys.Prestamo;
import io.github.alfredgood.api_ciber.modelo.dto.create.PrestamoCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.PrestamoDTO;
import io.github.alfredgood.api_ciber.repositorio.ClienteRepo;
import io.github.alfredgood.api_ciber.repositorio.ComputadoraRepo;
import io.github.alfredgood.api_ciber.repositorio.ConsolaRepo;
import io.github.alfredgood.api_ciber.repositorio.JuegoRepo;
import io.github.alfredgood.api_ciber.repositorio.PrestamoRepo;
import jakarta.transaction.Transactional;

@Service
public class PrestamoServ {

    private final PrestamoRepo prestamoRepo;
    private final PrestamoMapper mapper;
    private final ClienteRepo clienteRepo;
    private final JuegoRepo juegoRepo;
    private final ConsolaRepo consolaRepo;
    private final ComputadoraRepo computadoraRepo;


    public PrestamoServ(PrestamoRepo prestamoRepo, PrestamoMapper mapper, ClienteRepo clienteRepo, JuegoRepo juegoRepo,
            ConsolaRepo consolaRepo, ComputadoraRepo computadoraRepo) {
        this.prestamoRepo = prestamoRepo;
        this.mapper = mapper;
        this.clienteRepo = clienteRepo;
        this.juegoRepo = juegoRepo;
        this.consolaRepo = consolaRepo;
        this.computadoraRepo = computadoraRepo;
    }

    public PrestamoDTO obtenerPorId(long id){
        Prestamo prestamo= prestamoRepo.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException("Prestamo id "+id+" no encontrado"));

        return mapper.toDTO(prestamo);
    }

    @Transactional
    public PrestamoDTO nuevo(PrestamoCreateDTO create){

        long clienteId= create.clienteId();
        Cliente cliente= clienteRepo.findById(clienteId)
            .orElseThrow(()-> new RecursoNoEncontradoException("Cliente id "+clienteId+" no encontrado"));

        
        Long productoId=create.productoId();

        switch (create.tipoProducto()) {

            case CONSOLA:
                Consola consola = consolaRepo.findById(productoId)
                    .orElseThrow(()-> new RecursoNoEncontradoException("Consola con id "+productoId+" no encontrado"));
                
                if(!consola.isDisponible()){
                    throw new RecursoNoDisponible("Consolas disponibles, se encuentran ocupadas");
                }

                if(consola.getStack()<=0){
                    throw new RecursoNoDisponible("No hay consolas disponible");
                }

                consola.setStack(consola.getStack()-1);

                if(consola.getStack()<=0){
                    consola.setDisponible(false);
                }

                consolaRepo.save(consola);
                break;

            case COMPUTADORA:
                Computadora computadora= computadoraRepo.findById(productoId)
                    .orElseThrow(()-> new RecursoNoEncontradoException("Computadora con id "+productoId+" no encontrado"));
                
                if(!computadora.isDisponible()){
                    throw new RecursoNoDisponible("Computadora no disponible, se encuentra ocupada");
                }

                computadora.setDisponible(false);
                computadoraRepo.save(computadora);
                break;

            case JUEGO:
                Juego juego= juegoRepo.findById(productoId)
                    .orElseThrow(()-> new RecursoNoEncontradoException("Juego con id "+productoId+" no encontrado"));

                if(!juego.isDisponible() || juego.getStack()<=0){
                   throw  new RecursoNoDisponible("No hay juego en stock");
                }

                juego.setStack(juego.getStack()-1);
                juegoRepo.save(juego);
                break;

            case UTILIDAD:
                throw new RecursoNoDisponible("Producto tipo utilidad no se puede prestar");
        }

        Prestamo prestamo=mapper.toEntity(create, cliente);
        prestamoRepo.save(prestamo);

        return mapper.toDTO(prestamo);
    }


    public List<PrestamoDTO> listaCompleta(){
        return mapper.toListDTO(prestamoRepo.findAll());
    }


    @Transactional
    public PrestamoDTO devolver(long id){
        Prestamo prestamo= prestamoRepo.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException("Prestamo id "+id+" no encontrado"));
        
        long productoId=prestamo.getProductoId();
        switch (prestamo.getTipoProducto()) {

            case CONSOLA:
                Consola consola=consolaRepo.findById(productoId).get();

                consola.setDisponible(true);
                consolaRepo.save(consola);
                break;

            case COMPUTADORA:
                Computadora computadora=computadoraRepo.findById(productoId).get();

                computadora.setDisponible(true);
                computadoraRepo.save(computadora);
                break;

            case JUEGO:
                Juego juego= juegoRepo.findById(productoId).get();

                juego.setStack(juego.getStack()+1);
                juegoRepo.save(juego);
                break;
            case UTILIDAD:
                throw new RecursoNoDisponible("Producto tipo utilidad no se puede prestar");
        }

        prestamo.setDevuelto(true);
        prestamoRepo.save(prestamo);

        return mapper.toDTO(prestamo);
    }

    public void eliminarPorId(long id){
        Prestamo prestamo=prestamoRepo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Prestamo id "+id+" no encontrado"));

        if(!prestamo.isDevuelto()){
            throw new RecursoNoDisponible("El producto no se ha devuelto");
        }

        prestamoRepo.delete(prestamo);
    }

    public List<PrestamoDTO> listaNoDevueltos(){
        List<Prestamo> prestamos=prestamoRepo.findAll()
            .stream()
            .filter(p -> !p.isDevuelto())
            .toList();

        return mapper.toListDTO(prestamos);
    }

    public List<PrestamoDTO> listaDevueltos(){
        List<Prestamo> prestamos=prestamoRepo.findAll()
            .stream()
            .filter(p -> p.isDevuelto())
            .toList();
        
        return mapper.toListDTO(prestamos);
    }
}