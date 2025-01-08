package com.aluradesafio.Literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Idioma")

public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "idiomas", cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    public Idioma(){
    }
    public Idioma(String nombre){
        this.nombre =nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    @Override
    public String toString(){
        return "Idioma{" +
                "id=" +id +
                ", nombre'" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Idioma)) return false;
        Idioma idioma = (Idioma) o;
        return id == idioma.id && Objects.equals(nombre, idioma.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
