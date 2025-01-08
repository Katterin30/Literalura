package com.aluradesafio.Literalura.service;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.model.Autor2;
import com.aluradesafio.Literalura.model.Idioma;
import com.aluradesafio.Literalura.model.Libro;
import com.aluradesafio.Literalura.repository.RepositorioAutor;
import com.aluradesafio.Literalura.repository.RepositorioIdioma;
import com.aluradesafio.Literalura.repository.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ConsumoAPI {
    @Autowired
    RepositorioLibro repositorioLibro;
    @Autowired
    RepositorioAutor repositorioAutor;
    @Autowired
    RepositorioIdioma repositorioIdioma;

    public String obtenerDatos(String url) {
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
       .build();
       HttpResponse<String> response = null;
    try {
    response = client
      .send(request, HttpResponse.BodyHandlers.ofString());
     } catch (IOException e) {
     throw new RuntimeException(e);
     } catch (InterruptedException e) {
        throw new RuntimeException(e);
     }
     String json = response.body();
     System.out.println(json);
      return response.body();
    }

    public void procesarYGuardarDatos(ApiResponse apiResponse) {
        apiResponse.libros().forEach(libroApi -> {
            Libro libro = new Libro();
            libro.setTitulo(libroApi.titulo());
            libro.setCantidadDescargas(libroApi.cantidadDescargas());

            List<Autor2> autores = libroApi.autores().stream().map(autorApi -> {
                Autor2 autor = repositorioAutor.findByNombreContainingIgnoreCase(autorApi.nombre())
                        .stream().findFirst().orElse(null);
                if (autor == null) {
                    autor = new Autor2();
                    autor.setNombre(autorApi.nombre());
                    autor.setAnioNacimiento(autorApi.anioNacimiento());
                    autor.setAnioFallecimiento(autorApi.anioFallecimiento());
                    autor = repositorioAutor.saveAndFlush(autor);
                }
                return autor;
            }).toList();
            libro.setAutores(autores);

            List<Idioma> idiomas = libroApi.idiomas().stream().map(idiomanNombre -> {
                return repositorioIdioma.findByNombre(idiomanNombre)
                        .orElseGet(() -> {
                            Idioma nuevoIdioma = new Idioma();
                            nuevoIdioma.setNombre(idiomanNombre);
                            return repositorioIdioma.save(nuevoIdioma);
                        });
            }).toList();
            libro.setIdiomas(idiomas);

            repositorioLibro.saveAndFlush(libro);
        });
    }
}