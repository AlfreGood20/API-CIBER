package io.github.alfredgood.api_ciber.modelo.Entitys;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.alfredgood.api_ciber.modelo.enumerado.TipoProducto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(updatable = false, name = "fecha_prestamo")
    private LocalDateTime fechaPrestamo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_devolver")
    private LocalDate fechaDevolver;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    private boolean devuelto;

    @ManyToOne
    @JoinColumn(nullable = false, name ="cliente_id")
    private Cliente cliente;

    @Column(nullable = false, name = "producto_id")
    private long productoId;
}