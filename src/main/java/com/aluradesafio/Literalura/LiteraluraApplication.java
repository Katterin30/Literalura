package com.aluradesafio.Literalura;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.principal.MenuPrincipal;
import com.aluradesafio.Literalura.service.ConsumoAPI;
import com.aluradesafio.Literalura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    public static void main(String[] args) { SpringApplication.run(LiteraluraApplication.class, args); }
    @Override
    public void run(String... args) throws Exception {
        var menuPrincipal =new MenuPrincipal();
        menuPrincipal.cargarDatos();
        menuPrincipal.mostrarMenu();
    }
}