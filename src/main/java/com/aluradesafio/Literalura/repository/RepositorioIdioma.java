package com.aluradesafio.Literalura.repository;

import com.aluradesafio.Literalura.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioIdioma extends JpaRepository<Idioma, Long> {
    Optional<Idioma> findByNombre(String nombre);
}
