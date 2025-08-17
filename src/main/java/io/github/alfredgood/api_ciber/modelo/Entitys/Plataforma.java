package io.github.alfredgood.api_ciber.modelo.Entitys;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name="Plataformas")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String nombre;

    @OneToMany(mappedBy = "plataforma")
    private List<Juego>juegos;
}