package com.aluradesafio.Literalura.repository;

import com.aluradesafio.Literalura.model.Autor2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor2, Long> {
    List<Autor2> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT a.nombre, a.anioNacimiento, a.anioFallecimiento " +
           "FROM autor2 a " +
           "WHERE a.anioNacimiento <= :anio1 " +
           "AND a.anioFallecimiento >= :anio2 " +
           "GROUP BY a.nombre, a.anioNacimiento, a.anioFallecimiento")
    List<Object[]> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqualGrouped(
            int anio1, int anio2);
    List<Autor2> findByAnioNacimientoLessThanEqualAndAnioFallecimientoIsNull(int anio);
}
