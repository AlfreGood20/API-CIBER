package io.github.alfredgood.api_ciber.modelo.Entitys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;

import io.github.alfredgood.api_ciber.modelo.enumerado.EstadoVenta;
import io.github.alfredgood.api_ciber.modelo.enumerado.TipoDePago;
import io.github.alfredgood.api_ciber.modelo.enumerado.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @Column(name = "producto_id", nullable = false)
    private long productoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenta estado=EstadoVenta.VENDIDO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pago", nullable = false)
    private TipoDePago tipoPago;

    @Column(name = "codigo_venta", nullable = true)
    private String codigoVenta;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @Column(updatable = false)
    private double total;

    @PostPersist //ESTO SE USA DESPUES DEL GUARDADO POR QUE SE USA EL ID
    private void colocarCodigoVenta(){
        //EJEMPLO JUE-FECHA_VENTA-IDVENTA
        StringBuilder codigo= new StringBuilder();

        String prefigo=tipoProducto.toString().substring(0, 3);
        String fecha=fechaRegistro.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        codigo.append(prefigo +"-"+ fecha +"-"+ String.format("%04d", this.id));

        this.codigoVenta=codigo.toString();
    }

    @PrePersist
    @PreUpdate
    private void datosAutomaticos(){
        this.total=precioUnitario*cantidad;
    }
}