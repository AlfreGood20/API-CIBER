package io.github.alfredgood.api_ciber.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.alfredgood.api_ciber.excepciones.RecursoNoDisponible;
import io.github.alfredgood.api_ciber.excepciones.RecursoNoEncontradoException;
import io.github.alfredgood.api_ciber.mapper.VentaMapper;
import io.github.alfredgood.api_ciber.modelo.Entitys.Juego;
import io.github.alfredgood.api_ciber.modelo.Entitys.Utilidad;
import io.github.alfredgood.api_ciber.modelo.Entitys.Venta;
import io.github.alfredgood.api_ciber.modelo.dto.create.VentaCreateDTO;
import io.github.alfredgood.api_ciber.modelo.dto.response.VentaDTO;
import io.github.alfredgood.api_ciber.modelo.enumerado.EstadoVenta;
import io.github.alfredgood.api_ciber.repositorio.JuegoRepo;
import io.github.alfredgood.api_ciber.repositorio.UtilidadRepo;
import io.github.alfredgood.api_ciber.repositorio.VentaRepo;
import jakarta.transaction.Transactional;

@Service
public class VentaServ {

    private final VentaRepo ventaRepo;
    private final VentaMapper mapper;
    private final JuegoRepo juegoRepo;
    private final UtilidadRepo utilidadRepo;

    public VentaServ(VentaRepo ventaRepo, VentaMapper mapper, JuegoRepo juegoRepo, UtilidadRepo utilidadRepo) {
        this.ventaRepo = ventaRepo;
        this.mapper = mapper;
        this.juegoRepo = juegoRepo;
        this.utilidadRepo = utilidadRepo;
    }

    @Transactional
    public VentaDTO nuevo(VentaCreateDTO create) {

        long productoId = create.productoId();
        double precioUnitario = 0.0;

        switch (create.tipoProducto()) {

            case JUEGO:
                Juego juego = juegoRepo.findById(productoId)
                        .orElseThrow(
                                () -> new RecursoNoEncontradoException("El juego id " + productoId + " no encontrado"));

                if (!juego.isVendible()) {
                    throw new RecursoNoDisponible("El juego " + juego.getTitulo() + " no esta a la venta");
                }

                if (juego.getStack() <= 0) {
                    throw new RecursoNoDisponible("El juego " + juego.getTitulo() + " no hay en stock");
                }

                if (create.cantidad() > juego.getStack()) {
                    throw new RecursoNoDisponible("Exede el limite del stock");
                }

                juego.setStack(juego.getStack() - create.cantidad());
                juegoRepo.save(juego);

                precioUnitario = juego.getPrecioVenta();
                break;

            case UTILIDAD:

                Utilidad utilidad = utilidadRepo.findById(productoId)
                        .orElseThrow(() -> new RecursoNoEncontradoException(
                                "La utilidad id " + productoId + " no encontrado"));

                if (utilidad.getStack() <= 0) {
                    throw new RecursoNoDisponible("Utilidad " + utilidad.getNombre() + " no hay en stock");
                }

                if (create.cantidad() > utilidad.getStack()) {
                    throw new RecursoNoDisponible("Exede el limite del stock");
                }

                utilidad.setStack(utilidad.getStack() - create.cantidad());
                utilidadRepo.save(utilidad);

                precioUnitario = utilidad.getPrecio();
                break;

            case CONSOLA:
                throw new RecursoNoDisponible("Tipo de producto consola no estan a la venta");

            case COMPUTADORA:
                throw new RecursoNoDisponible("Tipo de producto computadora no estan a la venta");
        }

        Venta venta = mapper.toEntity(create, precioUnitario);
        ventaRepo.save(venta);

        return mapper.tDTO(venta);
    }

    public List<VentaDTO> listaTodos() {
        return mapper.toListDTO(ventaRepo.findAll());
    }

    @Transactional
    public VentaDTO actualizarEstado(long id, EstadoVenta estado) {

        Venta venta=ventaRepo.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException("Venta con id "+id+" no encontrado"));

         if(venta.getEstado() == EstadoVenta.CANCELADO){
            throw new RecursoNoDisponible("Esta venta con codigo "+venta.getCodigoVenta()+" se encuentra cancelada");
        }

        if(estado == EstadoVenta.CANCELADO){

            long idProducto=venta.getProductoId();
            int cantidad=venta.getCantidad();

            switch (venta.getTipoProducto()) {
                case JUEGO:

                    Juego juego= juegoRepo.findById(idProducto).orElseThrow(()-> new RecursoNoEncontradoException("Juego con id "+idProducto+ " no encontrado"));

                    juego.setStack(juego.getStack()+cantidad);
                    juegoRepo.save(juego);
                    break; 

                case UTILIDAD:
                    Utilidad utilidad= utilidadRepo.findById(idProducto).orElseThrow(()-> new RecursoNoEncontradoException("Utilidad con id "+idProducto+ " no encontrado"));

                    utilidad.setStack(utilidad.getStack()+cantidad);
                    utilidadRepo.save(utilidad);
                    break;
            
                default:
                    break;
            }
        }

        venta.setEstado(estado);
        ventaRepo.save(venta);

        return mapper.tDTO(venta);
    }

    public void eliminarPorId(long id) {
        Venta venta = ventaRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta id " + id + " no encontrado"));

        ventaRepo.delete(venta);
    }

    public List<VentaDTO> listaCompletadas() {
        List<Venta> venta = ventaRepo.findAll()
                .stream()
                .filter(v -> v.getEstado() == EstadoVenta.VENDIDO)
                .toList();

        return mapper.toListDTO(venta);
    }

    public List<VentaDTO> listaCancelados() {
        List<Venta> venta = ventaRepo.findAll()
                .stream()
                .filter(v -> v.getEstado() == EstadoVenta.CANCELADO)
                .toList();

        return mapper.toListDTO(venta);
    }

    public List<VentaDTO> listaPendientes() {
        List<Venta> venta = ventaRepo.findAll()
                .stream()
                .filter(v -> v.getEstado() == EstadoVenta.PENDIENTE)
                .toList();

        return mapper.toListDTO(venta);
    }
}