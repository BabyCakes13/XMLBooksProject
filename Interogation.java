import book.Book;
import author.Author;
import genre.Genre;
import java.util.ArrayList;
import org.w3c.dom.Element;

public class Interogation {
  private DOMparser booksParser;
  private DOMparser authorsParser;
  private DOMparser genresParser;

  public Interogation(DOMparser booksParser,
                      DOMparser authorsParser,
                      DOMparser genresParser) {
    this.booksParser = booksParser;
    this.authorsParser = authorsParser;
    this.genresParser = genresParser;
  }

  public ArrayList<Author> getEnglishAuthors() {
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
    // TODO
    return null;
  }

  public ArrayList<Book> getColombianRomanceBooks() {

    return null;
  }

  public ArrayList<Author> getAuthorsWithDystopianGenre() {
    // TODO
    return null;
  }

  public ArrayList<Author> getEnglishAuthorsAlive() {
    // TODO
    return null;
  }

  public ArrayList<Genre> getAllEnglishGenres() {
    // TODO
    return null;
  }
}
