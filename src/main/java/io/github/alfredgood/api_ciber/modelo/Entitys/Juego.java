package io.github.alfredgood.api_ciber.modelo.Entitys;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int stack;

    @Column(nullable = false, name = "precio_venta")
    private double precioVenta;

    @Column(name = "precio_renta")
    private double precioRenta;

    @Column(nullable = false)
    private boolean vendible;

    private boolean disponible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plataforma_id", nullable = false)
    private Plataforma plataforma;

    @PrePersist
    @PreUpdate
    private void datosAutomaticos() {
        double comision = 0.05;
        this.precioRenta = precioVenta * comision; // INSERTAR AUTOMATICAMENTE EL PRECIO DE LA RENTA
        this.disponible = stack > 0; // INSERTAR SI ESTA DISPONIBLE SI HAY EN EL STACK
    }
}
