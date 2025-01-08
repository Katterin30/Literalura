package com.aluradesafio.Literalura.service;

import com.aluradesafio.Literalura.model.Autor2;
import com.aluradesafio.Literalura.repository.RepositorioAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    RepositorioAutor repositorioAutor;

    public Autor2 crearAutor(Autor2 autor) {
        return repositorioAutor.saveAndFlush(autor);
    }

    public List<Autor2> buscarAutor(String nombre) {
        return repositorioAutor.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Autor2> listarAutores(){
        return repositorioAutor.findAll();
    }

    public List<Autor2> listarAutoresVivosEnAnio(int anio) {
        return repositorioAutor.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqualGrouped(anio, anio).stream()
                .map(objects -> {
                    Autor2 autor = new Autor2();
                    autor.setNombre((String) objects[0]);
                    autor.setAnioNacimiento((Integer) objects[1]);
                    autor.setAnioFallecimiento((Integer) objects[2]);
                    return autor;
                }).toList();
    }
}
