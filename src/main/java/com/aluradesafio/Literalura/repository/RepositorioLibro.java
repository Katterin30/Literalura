package com.aluradesafio.Literalura.repository;

import com.aluradesafio.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioLibro extends JpaRepository<Libro,Long> {
    long countByIdiomasNombre(String nombreIdioma);
    //Metodo personalizacion opcional
    //List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
