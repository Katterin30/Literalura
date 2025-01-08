package com.aluradesafio.Literalura;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.model.Autor2;
import com.aluradesafio.Literalura.model.Libro;
import com.aluradesafio.Literalura.principal.MenuPrincipal;
import com.aluradesafio.Literalura.service.AutorService;
import com.aluradesafio.Literalura.service.ConsumoAPI;
import com.aluradesafio.Literalura.service.ConvierteDatos;
import com.aluradesafio.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    LibroService libroService;
    @Autowired
    AutorService autorService;
    @Autowired
    ConsumoAPI consumoAPI;
    @Autowired
    ConvierteDatos convierteDatos;

    public static void main(String[] args) { SpringApplication.run(LiteraluraApplication.class, args); }
    @Override
    public void run(String... args) throws Exception {
        //Para consumir de la buena (Api xd)
        String url= "https://gutendex.com/books/";
        String json= consumoAPI.obtenerDatos(url);
        ApiResponse apiResponse =convierteDatos.obtenerDatos(json,ApiResponse.class);

        //var autor1 = new Autor2();
        //autor1.setNombre("Joel");
        //autor1.setAnioNacimiento(1995);
       // autor1.setAnioFallecimiento(2024);
        //autor1 = autorService.crearAutor(autor1);
        //var autor2 = new Autor2();
        //autor2.setNombre("katerin");
        //autor2.setAnioNacimiento(1994);
       // autor2.setAnioFallecimiento(2023);
        //autor2 = autorService.crearAutor(autor2);
        //List<Autor2> autores = new ArrayList<>();
        //autores.add(autor1);
        //autores.add(autor2);

        //var libro= new Libro();
        //libro.setTitulo("Playa Basurero");
        //libro.setCantidadDescargas(777);
        //libro.setAutores(autores);
        //libroService.crearLibro(libro);
        //autorService.buscarAutor("joel").forEach(System.out::println);

        //libroService.guardarDatosDesdeApi(apiResponse);
        libroService.listarLibros().forEach(System.out::println);
        autorService.listarAutores().forEach(System.out::println);
        System.out.println(libroService.contarLibrosPorIdioma("en"));
        var menuPrincipal = new MenuPrincipal(autorService);
        menuPrincipal.cargarDatos();
        menuPrincipal.mostrarMenu();
    }
}