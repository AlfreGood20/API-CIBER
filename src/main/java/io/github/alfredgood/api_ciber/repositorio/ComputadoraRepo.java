package io.github.alfredgood.api_ciber.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.alfredgood.api_ciber.modelo.Entitys.Computadora;

@Repository
public interface ComputadoraRepo extends JpaRepository<Computadora,Long>{
}