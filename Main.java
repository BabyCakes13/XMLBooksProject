import java.util.ArrayList;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {
  public static void main(String[] args) {
    System.out.println("Starting the application.");

    // unmarshal
    try {
       File file = new File("xml_databases/books.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Books books_element = (Books) jaxbUnmarshaller.unmarshal(file);

       System.out.println(books_element);

       ArrayList<Book> books = books_element.getBooks();
       System.out.println(books);

       for (Book book: books) {
         System.out.println(book.getId() + " " +
                            book.getTitle() + " (" +
                            book.getAuthor() + " (" +
                            book.getGenre() + ")");
        }
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }
}
