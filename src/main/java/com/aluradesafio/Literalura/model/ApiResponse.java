package com.aluradesafio.Literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiResponse
        (@JsonProperty("count") int count,
         @JsonProperty("next") String next,
         @JsonProperty("previous") String previous,
         @JsonProperty("results") List<DatosLibro> libros
        ) {
}
