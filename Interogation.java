import book.Book;
import author.Author;
import genre.Genre;
import java.util.HashSet;
import java.util.Set;
import org.w3c.dom.Element;
import java.io.File;

public class Interogation {
  private DOMparser parser;
/**
* Interogation class.
*
* Having been given three parsers for the books,
* authors and genres, the Interogation class is
* used for querying from the XML databases, based
* on some questions.
*/
  public Interogation(DOMparser parser) {
    this.parser = parser;
  }

  public Set<String> getEnglishAuthors() {
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("author")) != null){
        String nationality =
          this.parser.extractElementFromTag(element, "nationality");
        String name =
          this.parser.extractElementFromTag(element, "name");

        if (nationality.equals("English")) {
          result.add(name);
        }
    }
    return result;
  }

  public Set<String> getRomanceBooks() {
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("book")) != null){
        String genre =
          this.parser.extractElementFromTag(element, "genre");
        String title =
          this.parser.extractElementFromTag(element, "title");

        if (genre.equals("Romance")) {
          result.add(title);
        }
    }
    return result;
  }

  public Set<String> getColombianRomanceBooks() {
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("author")) != null){
      Set<String> partialResult = new HashSet<>();
        String nationality =
          this.parser.extractElementFromTag(element, "nationality");
        String authorName =
          this.parser.extractElementFromTag(element, "name");

        if (nationality.equals("Colombian")) {
          partialResult = this.getAllRomanceBooksOfAuthor(authorName);
          result.addAll(partialResult);
        }
    }
    return result;
  }

  public Set<String> getAllRomanceBooksOfAuthor(String authorName) {
    Element element = null;
    Set<String> result = new HashSet<>();

    while((element = this.parser.nextElement("book")) != null){
        String author =
          this.parser.extractElementFromTag(element, "author");
        String title =
          this.parser.extractElementFromTag(element, "title");
        String genre =
          this.parser.extractElementFromTag(element, "genre");

        if ((author.equals(authorName)) && (genre.equals("Romance"))) {
          result.add(title);
        }
    }
    return result;
  }

  public Set<String> getAuthorsWithDystopianGenre() {
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("book")) != null){
        String genre =
          this.parser.extractElementFromTag(element, "genre");
        String author =
          this.parser.extractElementFromTag(element, "author");

        if (genre.equals("Dystopian")) {
          result.add(author);
        }
    }
    return result;
  }

  public Set<String> getEnglishAuthorsAlive() {
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("author")) != null){
        String deathYear =
          this.parser.extractElementFromTag(element, "deathYear");
        String nationality =
          this.parser.extractElementFromTag(element, "nationality");
        String name =
          this.parser.extractElementFromTag(element, "name");

        if ((deathYear.equals("-")) && (nationality.equals("English"))) {
          result.add(name);
        }
    }
    return result;
  }

  public Set<String> getAllEnglishGenres() {
    Set<String> result = new HashSet<>();
    Set<String> authors = this.getEnglishAuthors();
    Element element = null;

    while((element = this.parser.nextElement("book")) != null){
        String genre =
          this.parser.extractElementFromTag(element, "genre");
        String author =
          this.parser.extractElementFromTag(element, "author");

        if (authors.contains(author)) {
          result.add(genre);
        }
    }
    return result;
  }
}
