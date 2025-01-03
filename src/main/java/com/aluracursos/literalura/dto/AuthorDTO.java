package com.aluracursos.literalura.dto;
import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDTO(
        @JsonAlias("name")
        String authorName,
        @JsonAlias("birth_year")
        Integer birthYear,
        @JsonAlias("death_year")
        Integer deathYear
) {

    @Override
    public String toString() {
        return "------ Autor ------" +
                "\n Nombre: " + authorName +
                "\n Fecha de nacimiento: " + birthYear +
                "\n Fecha de muerte: " + deathYear +
                "\n -----------------\n";
    }
}
