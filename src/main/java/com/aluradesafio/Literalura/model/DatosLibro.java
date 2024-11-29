package com.aluradesafio.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonProperty("title") String titulo,
        @JsonProperty("download_count") int cantidadDescargas,
        @JsonProperty("authors") List<Autor> autores) {
}
