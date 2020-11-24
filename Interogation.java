import book.Book;
import author.Author;
import genre.Genre;
import java.util.ArrayList;
import org.w3c.dom.Element;

public class Interogation {
  private DOMparser booksParser;
  private DOMparser authorsParser;
  private DOMparser genresParser;

/**
* Interogation class.
* 
* Having been given three parsers for the books,
* authors and genres, the Interogation class is
* used for querying from the XML databases, based
* on some questions.
*/
  public Interogation(DOMparser booksParser,
                      DOMparser authorsParser,
                      DOMparser genresParser) {
    this.booksParser = booksParser;
    this.authorsParser = authorsParser;
    this.genresParser = genresParser;
  }

  public ArrayList<Author> getEnglishAuthors() {
    System.out.println("\nQuerry for all English authors:");
    Element element = null;

    while((element = this.authorsParser.nextElement()) != null){
        String nationality =
          this.authorsParser.extractElementFromTag(element, "nationality");
        String name =
          this.authorsParser.extractElementFromTag(element, "name");

        if (nationality.equals("English")) {
          System.out.println(name);
        }
    }
    return null;
  }

  public ArrayList<Book> getRomanceBooks() {
    System.out.println("\nQuerry for all romance genre books:");
    Element element = null;

    while((element = this.booksParser.nextElement()) != null){
        String genre =
          this.booksParser.extractElementFromTag(element, "genre");
        String title =
          this.booksParser.extractElementFromTag(element, "title");

        if (genre.equals("Romance")) {
          System.out.println(title);
        }
    }
    return null;
  }

  public ArrayList<Book> getColombianRomanceBooks() {
    System.out.println("\nQuerry for all Colombian romance books.");

    return null;
  }

  public ArrayList<Author> getAuthorsWithDystopianGenre() {
    System.out.println("\nQuerry for all authors with dystopian books.");
    return null;
  }

  public ArrayList<Author> getEnglishAuthorsAlive() {
    System.out.println("\nQuerry for all English authorts still alive:");
    return null;
  }

  public ArrayList<Genre> getAllEnglishGenres() {
    System.out.println("\nQuerry for all English genres:");
    return null;
  }
}
