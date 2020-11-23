import java.util.ArrayList;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {
  public static void main(String[] args) {
    System.out.println("Starting the application.");

    // unmarshal from hand-written (for now) XML DB.
    try {
       File file = new File("xml_databases/books.xml");
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
}
