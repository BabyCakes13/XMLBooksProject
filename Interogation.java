import book.Book;
import author.Author;
import genre.Genre;
import java.util.ArrayList;
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

  public ArrayList<String> getEnglishAuthors() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;

    while((element = this.authorsParser.nextElement()) != null){
        String nationality =
          this.authorsParser.extractElementFromTag(element, "nationality");
        String name =
          this.authorsParser.extractElementFromTag(element, "name");

        if (nationality.equals("English")) {
          result.add(name);
        }
    }
    return result;
  }

  public ArrayList<String> getRomanceBooks() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;

    while((element = this.booksParser.nextElement()) != null){
        String genre =
          this.booksParser.extractElementFromTag(element, "genre");
        String title =
          this.booksParser.extractElementFromTag(element, "title");

        if (genre.equals("Romance")) {
          result.add(title);
        }
    }
    return result;
  }

  public ArrayList<String> getColombianRomanceBooks() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;

    while((element = this.authorsParser.nextElement()) != null){
      ArrayList<String> partialResult = new ArrayList<>();
        String nationality =
          this.authorsParser.extractElementFromTag(element, "nationality");
        String authorName =
          this.authorsParser.extractElementFromTag(element, "name");

        if (nationality.equals("Colombian")) {
          partialResult = this.getAllRomanceBooksOfAuthor(authorName);
          result.addAll(partialResult);
        }
    }
    return result;
  }

  public ArrayList<String> getAllRomanceBooksOfAuthor(String authorName) {
    Element element = null;
    ArrayList<String> result = new ArrayList<>();

    while((element = this.booksParser.nextElement()) != null){
        String author =
          this.booksParser.extractElementFromTag(element, "author");
        String title =
          this.booksParser.extractElementFromTag(element, "title");
        String genre =
          this.booksParser.extractElementFromTag(element, "genre");

        if ((author.equals(authorName)) && (genre.equals("Romance"))) {
          result.add(title);
        }
    }
    return result;
  }

  public ArrayList<String> getAuthorsWithDystopianGenre() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;

    while((element = this.booksParser.nextElement()) != null){
        String genre =
          this.booksParser.extractElementFromTag(element, "genre");
        String author =
          this.booksParser.extractElementFromTag(element, "author");

        if (genre.equals("Dystopian")) {
          result.add(author);
        }
    }
    return result;
  }

  public ArrayList<String> getEnglishAuthorsAlive() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;

    while((element = this.authorsParser.nextElement()) != null){
        String deathYear =
          this.authorsParser.extractElementFromTag(element, "deathYear");
        String nationality =
          this.authorsParser.extractElementFromTag(element, "nationality");
        String name =
          this.authorsParser.extractElementFromTag(element, "name");

        if ((deathYear.equals("-")) && (nationality.equals("English"))) {
          result.add(name);
        }
    }
    return result;
  }

  public ArrayList<String> getAllEnglishGenres() {
    ArrayList<String> result = new ArrayList<>();
    ArrayList<String> authors = this.getEnglishAuthors();
    Element element = null;

    while((element = this.booksParser.nextElement()) != null){
        String genre =
          this.booksParser.extractElementFromTag(element, "genre");
        String author =
          this.booksParser.extractElementFromTag(element, "author");

        if (authors.contains(author)) {
          result.add(genre);
        }
    }
    return result;
  }
}
