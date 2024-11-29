package com.aluradesafio.Literalura.principal;

import com.aluradesafio.Literalura.model.ApiResponse;
import com.aluradesafio.Literalura.service.ConsumoAPI;
import com.aluradesafio.Literalura.service.ConvierteDatos;

import java.util.Scanner;

public class MenuPrincipal {
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private ApiResponse apiResponse;

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.cargarDatos();
        menuPrincipal.mostrarMenu();
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
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo(scanner);
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos(scanner);
                case 5 -> listarLibrosPorIdiomas();
                case 6 -> System.out.println("¡Gracias por usar el sistema! Saliendo...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 6);

        System.out.println(scanner);

        scanner.close();
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = scanner.nextLine();
        apiResponse.libros().stream()
                .filter(libro -> libro.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .forEach(System.out::println);
    }

    private void listarLibros() {
        System.out.println("=== Libros Registrados ===");
        apiResponse.libros().forEach(System.out::println);
    }

    private void listarAutores() {
        System.out.println("=== Autores Registrados ===");
        apiResponse.libros().stream()
                .flatMap(libro -> libro.autores().stream())
                .distinct()
                .forEach(System.out::println);
    }

    private void listarAutoresVivos (@org.jetbrains.annotations.NotNull Scanner scanner) {
        System.out.print("Ingrese el año a consultar: ");
        int anio = scanner.nextInt();
        apiResponse.libros().stream()
                .flatMap(libro -> libro.autores().stream())
                .filter(autor -> autor.anioNacimiento() != null && autor.anioNacimiento() <= anio &&
                        (autor.anioFallecimiento() == null || autor.anioFallecimiento() > anio))
                .distinct()
                .forEach(System.out::println);
    }

    private void listarLibrosPorIdiomas() {
        System.out.println("Esta funcionalidad aún está pendiente de implementación.");
        // Puedes agregar esta funcionalidad cuando los datos de idiomas estén disponibles.
    }
}