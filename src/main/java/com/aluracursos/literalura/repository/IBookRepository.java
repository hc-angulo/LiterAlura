package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository  extends JpaRepository<Book,Long> {
    List<Book> findBookByLanguage(String language);

    @Query("SELECT b FROM Book b ORDER BY b.downloads_count DESC LIMIT 10")
    List<Book> top10();
}
