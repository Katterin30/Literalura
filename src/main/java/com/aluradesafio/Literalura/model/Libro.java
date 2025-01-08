package com.aluradesafio.Literalura.model;

import jakarta.persistence.*;
import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int cantidadDescargas;
    //private List <Language>idioma;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor2> autores =new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name= "libro_idioma",
            joinColumns = @JoinColumn(name= "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List <Idioma> idiomas = new ArrayList<>();

    public Libro(){
    }

    public Libro(String titulo, int cantidadDescargas) {
        this.titulo = titulo;
        this.cantidadDescargas = cantidadDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public List<Autor2> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor2> autores) {
        this.autores = autores;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
