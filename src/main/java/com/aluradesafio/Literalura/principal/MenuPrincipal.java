package com.aluradesafio.Literalura.principal;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.model.Autor;
import com.aluradesafio.Literalura.model.Autor2;
import com.aluradesafio.Literalura.model.DatosLibro;
import com.aluradesafio.Literalura.service.AutorService;
import com.aluradesafio.Literalura.service.ConsumoAPI;
import com.aluradesafio.Literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MenuPrincipal {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private ApiResponse apiResponse;

    private AutorService autorService;

    public MenuPrincipal(AutorService autorService) {
        this.autorService = autorService;
    }

    public void cargarDatos() {
        String url = "https://gutendex.com/books/";
        String json = consumoAPI.obtenerDatos(url);
        apiResponse = convierteDatos.obtenerDatos(json, ApiResponse.class);
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Autores vivos en un determinado año");
            System.out.println("5. Listar libros por idiomas");
            System.out.println("6. Mostrar estadísticas por idioma");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo(scanner);
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos(scanner);
                case 5 -> listarLibrosPorIdiomas();
                case 6 -> mostrarEstadisticasPorIdioma(scanner);
                case 7 -> System.out.println("¡Gracias por usar el sistema! Saliendo...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 7);

        System.out.println(scanner);

        scanner.close();
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = scanner.nextLine();
        apiResponse.libros().stream()
                .filter(libro -> libro.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .forEach(item -> {
                    System.out.println("Titulo:" + item.titulo()+
                            "\n Autor: "+item.autores().stream().map(Autor::nombre).collect(Collectors.joining("; ")) +
                            "\n Idiomas: "+ item.idiomas().stream().collect(Collectors.joining(";")) +
                            "\n Numero de Descargas: " + item.cantidadDescargas());
                });
    }

    private void listarLibros() {
        System.out.println("=== Libros Registrados ===");
        apiResponse.libros().forEach(item ->{System.out.println( item.titulo());});
    }

    private void listarAutores() {
        System.out.println("=== Autores Registrados ===");
        apiResponse.libros().stream()
                .flatMap(libro -> libro.autores().stream().map(Autor::nombre))
                .distinct()
                .forEach(System.out::println);
    }

    private void listarAutoresVivos(Scanner scanner){
        System.out.println("Ingrese el año a consultar: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        if (anio < 0){
            System.out.println("El año debe ser un valor positivo y entero.");
            return;
        }
        List<Autor2> autoresVivos = autorService.listarAutoresVivosEnAnio(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autoresVivos.forEach(autor -> {
                System.out.printf("Nombre: %s, Año de nacimiento: %d, Año de fallecimiento: %s%n",
                        autor.getNombre(), autor.getAnioNacimiento(), autor.getAnioFallecimiento());
            });
        }
    }

    private void listarLibrosPorIdiomas() {
        System.out.println("\"=== Libros por idiomas ===");
        Map<String, Long> librosPorIdiomas =apiResponse.libros().stream()
                .flatMap(libro -> libro.idiomas().stream())
                .collect(Collectors.groupingBy(idioma -> idioma, Collectors.counting()));
        librosPorIdiomas.forEach((idioma, cantidad) ->
                        System.out.printf("Idioma: %s - Libros: %d%n", idioma, cantidad));
    }
    
    private void mostrarEstadisticasPorIdioma(Scanner scanner){
        System.out.println("Seleccione el idioma para ver estadísticas:");
        Map<String, Long> librosPorIdiomas = apiResponse.libros().stream()
                .flatMap(libro -> libro.idiomas().stream())
                .collect(Collectors.groupingBy(idioma -> idioma, Collectors.counting()));
        int index = 1;
        List<String> idiomasDisponibles = new ArrayList<>(librosPorIdiomas.keySet());
        for (String idioma : idiomasDisponibles) {
            System.out.printf("%d. %s%n", index++, idioma);
        }
        System.out.println("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); //limpiar buffer

        if (opcion< 1|| opcion > idiomasDisponibles.size()){
            System.out.println("Opción invalida");
            return;
        }
        String idiomaSeleccionado = idiomasDisponibles.get(opcion - 1);
        long cantidad = librosPorIdiomas.get(idiomaSeleccionado);
        long totalLibros = apiResponse.libros().size();
        double porcentaje = ((double) cantidad / totalLibros) * 100;

        System.out.printf("Idioma: %s%n", idiomaSeleccionado);
        System.out.printf("Cantidad de libros: %d%n", cantidad);
        System.out.printf("Porcentaje del total: %.2f%%%n", porcentaje);
    }

    private String imprimirLibro(DatosLibro libro) {
        return null;
    }
}