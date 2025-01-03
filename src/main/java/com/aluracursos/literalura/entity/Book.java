package com.aluracursos.literalura.entity;

import com.aluracursos.literalura.dto.BookDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;

    private String language;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    private Long downloads_count;

    public Book(){}

    public Book(BookDTO bookDTO){
        this.title = bookDTO.title();
        this.language = bookDTO.languages().get(0).toUpperCase();
        this.author = new Author(bookDTO.authors().get(0));
        this.downloads_count = bookDTO.downloads();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getDownloads_count() {
        return downloads_count;
    }

    public void setDownloads_count(Long downloads_count) {
        this.downloads_count = downloads_count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "------ Libro ------" +
                "\n Título: " + title +
                "\n Autor: " + (author != null ? author.getName() : "Autor no asignado") +
                "\n Idioma: " + language +
                "\n Número de descargas: " + downloads_count +
                "\n -----------------\n";
    }
}
