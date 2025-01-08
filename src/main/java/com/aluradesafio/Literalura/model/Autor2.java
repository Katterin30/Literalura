package com.aluradesafio.Literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "autor2")
public class Autor2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "anio_nacimiento")
    private Integer anioNacimiento;

    @Column(name = "anio_fallecimiento")
    private Integer anioFallecimiento;

    @ManyToMany(mappedBy = "autores",cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Libro> libros =new ArrayList<>();

    public Autor2(){
    }
    public Autor2(String nombre, Integer anioNacimiento, Integer anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    @Override
    public String toString() {
        return "Autor2{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anioNacimiento=" + anioNacimiento +
                ", anioFallecimiento=" + anioFallecimiento +
                '}';
    }
}
