package io.github.alfredgood.api_ciber.modelo.Entitys;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "computadoras")
public class Computadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String marca;

    @Column(nullable = true)
    private String procesador;

    @Column(nullable = true)
    private Integer cantidadDeRam;

    @Column(nullable = true)
    private String tarjetaDeVideo;

    private boolean disponible;
}
