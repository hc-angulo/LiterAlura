package com.aluracursos.literalura.entity;

import com.aluracursos.literalura.dto.AuthorDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private Integer birthYear;

    private Integer deathYear;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.authorName();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();

    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "---------- Autor ----------" +
                "\n Nombre: " + name +
                "\n Fecha de nacimiento: " + birthYear +
                "\n Fecha de muerte: " + deathYear +
                "\n Libros: " +
                "\n -------------------------\n";
    }


}
