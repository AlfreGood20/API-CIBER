package io.github.alfredgood.api_ciber.modelo.Entitys;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="consolas")
public class Consola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String generacion;

    @Column(name = "precio_renta")
    private double precioRenta;

    @Column(nullable = false)
    private int stack;

    private boolean disponible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plataforma_id",nullable = false)
    private Plataforma plataforma;

    @PrePersist
    @PreUpdate
    private void datosAutomaticos(){
        this.disponible=stack>0;
    }
}