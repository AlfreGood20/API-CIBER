package io.github.alfredgood.api_ciber.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.alfredgood.api_ciber.modelo.Entitys.Prestamo;

@Repository
public interface PrestamoRepo extends JpaRepository<Prestamo,Long>{
}