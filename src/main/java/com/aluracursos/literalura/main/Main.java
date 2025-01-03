package com.aluracursos.literalura.main;

import com.aluracursos.literalura.dto.AuthorDTO;
import com.aluracursos.literalura.dto.JsonDTO;
import com.aluracursos.literalura.entity.Author;
import com.aluracursos.literalura.entity.Book;
import com.aluracursos.literalura.repository.IAuthorRepository;
import com.aluracursos.literalura.repository.IBookRepository;
import com.aluracursos.literalura.service.ConnectionAPI;
import com.aluracursos.literalura.service.DataConvertion;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private ConnectionAPI connectionAPI = new ConnectionAPI();
    private DataConvertion dataConvertion = new DataConvertion();
    private static final String API_URL = "https://gutendex.com/books/";

    private IBookRepository bookRepository;
    private IAuthorRepository authorRepository;

    public Main(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

    public void displayMenu() {
        int option = -1;

        while (option != 0) {
            var menu = """
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Buscar autor por nombre
                    4. Listar autores registrados
                    5. Listar autores vivos en un determinado año
                    6. Listar libros por idioma
                    7. Top 10 de libros con mayor número de descargas  
                    0. Salir
                    """;

            System.out.println("-------------------  Menú -------------------");
            System.out.println(menu);

            try {
                option = Integer.parseInt(sc.nextLine());

                sc.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Introduzca el nombre del libro: ");
                        getBook();
                        break;
                    case 2:
                        System.out.println("------------------- Libros registrados -------------------\n");
                        getBooks();
                        break;
                    case 3:
                        getAuthorByName();
                        break;
                    case 4:
                        getAuthors();
                        break;
                    case 5:
                        getLivingAuthors();
                        break;
                    case 6:
                        getBooksByLanguage();
                        break;
                    case 7:
                        System.out.println(" ---------- Top 10 de libros más descargados -------- \n");
                        getTop10Books();
                        break;
                    case 0:
                        System.out.println("Gracias por usar LiterAlura \n ");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes seleccionar un número.");
            }
        }
    }


    private void getBook() {
        String bookName = sc.nextLine();
        var json = connectionAPI.getData(API_URL + "?search=" + bookName.replace(" ","+"));
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);

        Optional<Book> book = results.bookResults().stream()
                .findFirst()
                .map(b -> new Book(b));

        if(book.isPresent()){
            Book foundBook = book.get();
            if(foundBook.getAuthor() != null){
                Author author = authorRepository.findAuthorByName(foundBook.getAuthor().getName());
                if(author == null){
                    //Crear y guardar en BBDD nuevo autor
                    Author newAuthor = foundBook.getAuthor();
                    author = authorRepository.save(newAuthor);
                }

                try{
                    foundBook.setAuthor(author);
                    bookRepository.save(foundBook);
                    System.out.println(foundBook);

                } catch (DataIntegrityViolationException e) {
                    System.out.println("El libro se encuentra registrado en la base de datos");

                }
            }
        }else{
            System.out.println("No se encontro registrado el libro: " + bookName);
        }
    }

    private void getBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void getAuthorByName() {
        System.out.println("Introduzca el nombre del autor: ");
        String authorName = sc.nextLine();
        var json = connectionAPI.getData(API_URL + "?search=" + authorName.replace(" ","+"));
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);

        Optional<AuthorDTO> author = results.bookResults().stream()
                .findFirst()
                .map(a -> new AuthorDTO(a.authors().get(0).authorName(),a.authors().get(0).birthYear(), a.authors().get(0).deathYear()));

                if(author.isPresent()){
                    System.out.println(author.get());
                }else{
                    System.out.println("No se encontró autor con el nombre: " + authorName);
                }
    }

    private void getAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void getLivingAuthors() {
        System.out.println("Ingrese el año en el que desea saber si un autor está o estaba vivo: ");
        try{
            int year = sc.nextInt();
            List<Author> authors = authorRepository.findAuthorBetweenYear(year);
            if(authors.isEmpty()){
                System.out.println("No se encontraron autores vivos en la base de datos en la fecha indicada.");
            }else{
                authors.forEach(System.out::println);
            }
        }catch(InputMismatchException e){
            System.out.println("Ingrese año válido.");
        }
    }

    private void getBooksByLanguage() {
        System.out.println("Ingrese el indioma en el que desea hacer su búsqueda: ");
        System.out.println("""
                es -> Español
                en -> Inglés
                fr -> Francés
                pt -> Portugués
                """);
        String language = sc.nextLine();

        List<Book> books = bookRepository.findBookByLanguage(language.toUpperCase());
        if(books.isEmpty()){
            System.out.println("No existen libros registrados en ese idioma.");
        }else{
            books.forEach(System.out::println);
        }
    }

    private void getTop10Books() {
        List<Book> books = bookRepository.top10();
        books.forEach(System.out::println);
    }



}
