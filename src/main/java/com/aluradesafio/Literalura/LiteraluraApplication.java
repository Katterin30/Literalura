package com.aluradesafio.Literalura;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.service.ConsumoAPI;
import com.aluradesafio.Literalura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoAPI();
        var json = consumoApi.obtenerDatos("https://gutendex.com/books/");
        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, ApiResponse.class);
        System.out.println(datos);
        datos.libros().forEach(System.out::println);

        for (var libro : datos.libros()) {
            System.out.println(libro);
        }
    }
}
