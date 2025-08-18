package io.github.alfredgood.api_ciber.modelo.Entitys;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "utilidades")
public class Utilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int stack;

    @Column(nullable = false)
    private double precio;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}