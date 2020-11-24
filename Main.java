import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Set;

import book.Book;
import book.Books;
import author.Author;
import author.Authors;
import genre.Genre;
import genre.Genres;

public class Main {
  public static void main(String[] args) {
    System.out.println("Starting the application.\n");

    loadBooks();
    loadAuthors();
    loadGenres();

    DOMparser booksParser = new DOMparser(new File("book/books.xml"), "book");
    DOMparser authorsParser = new DOMparser(new File("author/authors.xml"), "author");
    DOMparser genresParser = new DOMparser(new File("genre/genres.xml"), "genre");

    Interogation interogation = new Interogation(booksParser,
                                                 authorsParser,
                                                 genresParser);

    System.out.println("\nQuerry for all English authors:");
    print(interogation.getEnglishAuthors());
    System.out.println("\nQuerry for all romance genre books:");
    print(interogation.getRomanceBooks());
    System.out.println("\nQuerry for all Colombian romance books.");
    print(interogation.getColombianRomanceBooks());
    System.out.println("\nQuerry for all authors with dystopian books.");
    print(interogation.getAuthorsWithDystopianGenre());
    System.out.println("\nQuerry for all English authorts still alive:");
    print(interogation.getEnglishAuthorsAlive());
    System.out.println("\nQuerry for all English genres:");
    print(interogation.getAllEnglishGenres());
  }

  public static void loadBooks() {
    System.out.println("\nLoading books from XML into objects through unmarshalling...");

    try {
       File file = new File("book/books.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Books books_element = (Books) jaxbUnmarshaller.unmarshal(file);

       Set<Book> books = books_element.getBooks();

       for (Book book: books) {
         System.out.println(book);
        }
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }

  public static void loadAuthors() {
    System.out.println("\nLoading authors from XML into objects through unmarshalling...");

    try {
       File file = new File("author/authors.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Authors.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Authors authors_element = (Authors) jaxbUnmarshaller.unmarshal(file);

       Set<Author> authors = authors_element.getAuthors();

       for (Author author: authors) {
         System.out.println(author);
        }
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }

  public static void loadGenres() {
    System.out.println("\nLoading genres from XML into objects through unmarshalling...");

    try {
       File file = new File("genre/genres.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Genres.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Genres genres_element = (Genres) jaxbUnmarshaller.unmarshal(file);

       Set<Genre> genres = genres_element.getGenres();

       for (Genre genre: genres) {
         System.out.println(genre);
        }
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }

  public static void print(Set<String> list) {
    for(String s: list) {
      System.out.println(s);
    }
  }
}
