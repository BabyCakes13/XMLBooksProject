import java.util.ArrayList;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
  }

  public static void loadBooks() {
    System.out.println("\nLoading books from XML into objects through unmarshalling...");

    try {
       File file = new File("book/books.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Books books_element = (Books) jaxbUnmarshaller.unmarshal(file);

       ArrayList<Book> books = books_element.getBooks();

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

       ArrayList<Author> authors = authors_element.getAuthors();

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

       ArrayList<Genre> genres = genres_element.getGenres();

       for (Genre genre: genres) {
         System.out.println(genre);
        }
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }
}
