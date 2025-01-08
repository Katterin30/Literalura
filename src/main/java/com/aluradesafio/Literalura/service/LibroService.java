package com.aluradesafio.Literalura.service;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.model.Libro;
import com.aluradesafio.Literalura.repository.RepositorioAutor;
import com.aluradesafio.Literalura.repository.RepositorioIdioma;
import com.aluradesafio.Literalura.repository.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    RepositorioLibro repositorioLibro;
    @Autowired
    RepositorioIdioma repositorioIdioma;
    @Autowired
    ConsumoAPI consumoAPI;

    public void guardarDatosDesdeApi(ApiResponse apiResponse){
        consumoAPI.procesarYGuardarDatos(apiResponse);
    }
    public long contarLibrosPorIdioma(String idioma){
        return repositorioLibro.countByIdiomasNombre(idioma);
    }
     public List<Libro> listarLibros(){
        return repositorioLibro.findAll();
    }
}

