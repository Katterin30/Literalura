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

        libroService.listarLibros().forEach(System.out::println);
        autorService.listarAutores().forEach(System.out::println);
        System.out.println(libroService.contarLibrosPorIdioma("en"));

        // 🤝 Contribuciones
        // ¡Las contribuciones son bienvenidas! Si tienes ideas para mejorar **Literalura**, no dudes en abrir un issue o enviar un pull request. ¡Nos encanta colaborar!

        // 📜 Licencia
        // Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para obtener más detalles.

        // 🎉 Agradecimientos
        // Gracias por usar **Literalura**. ¡Esperamos que disfrutes trabajando en este proyecto tanto como nosotros disfrutamos creándolo!

        // ---

        // ¡Feliz codificación! 🚀
        var menuPrincipal = new MenuPrincipal(autorService);
        menuPrincipal.cargarDatos();
        menuPrincipal.mostrarMenu();
    }
}