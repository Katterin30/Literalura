package com.aluradesafio.Literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Autor(
        @JsonProperty("name") String nombre,
        @JsonProperty("birth_year") Integer anioNacimiento,
        @JsonProperty("death_year") Integer anioFallecimiento) {
}
