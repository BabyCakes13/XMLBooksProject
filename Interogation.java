import book.Book;
import author.Author;
import genre.Genre;
import java.util.ArrayList;
import java.util.ArrayList;
import org.w3c.dom.Element;
import java.io.File;

public class Interogation {
  private File libraryFile;
/**
* Interogation class.
*
* Having been given three parsers for the books,
* authors and genres, the Interogation class is
* used for querying from the XML databases, based
* on some questions.
*/
  public Interogation(File libraryFile) {
    this.libraryFile = libraryFile;
  }

  public ArrayList<String> getEnglishAuthors() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("author")) != null){
        String nationality =
          parser.extractElementFromTag(element, "nationality");
        String name =
          parser.extractElementFromTag(element, "name");

        if (nationality.equals("English")) {
          result.add(name);
        }
    }
    return result;
  }

  public ArrayList<String> getRomanceBooks() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("book")) != null){
        String genre =
          parser.extractElementFromTag(element, "genre");
        String title =
          parser.extractElementFromTag(element, "title");

        if (genre.equals("Romance")) {
          result.add(title);
        }
    }
    return result;
  }

  public ArrayList<String> getColombianRomanceBooks() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("author")) != null){
      ArrayList<String> partialResult = new ArrayList<>();
        String nationality =
          parser.extractElementFromTag(element, "nationality");
        String authorName =
          parser.extractElementFromTag(element, "name");

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
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("book")) != null){
        String author =
          parser.extractElementFromTag(element, "author");
        String title =
          parser.extractElementFromTag(element, "title");
        String genre =
          parser.extractElementFromTag(element, "genre");

        if ((author.equals(authorName)) && (genre.equals("Romance"))) {
          result.add(title);
        }
    }
    return result;
  }

  public ArrayList<String> getAuthorsWithDystopianGenre() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("book")) != null){
        String genre =
          parser.extractElementFromTag(element, "genre");
        String author =
          parser.extractElementFromTag(element, "author");

        if (genre.equals("Dystopian")) {
          result.add(author);
        }
    }
    return result;
  }

  public ArrayList<String> getEnglishAuthorsAlive() {
    ArrayList<String> result = new ArrayList<>();
    Element element = null;
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("author")) != null){
        String deathYear =
          parser.extractElementFromTag(element, "deathYear");
        String nationality =
          parser.extractElementFromTag(element, "nationality");
        String name =
          parser.extractElementFromTag(element, "name");

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
    DOMparser parser = new DOMparser(this.libraryFile);

    while((element = parser.nextElement("book")) != null){
        String genre =
          parser.extractElementFromTag(element, "genre");
        String author =
          parser.extractElementFromTag(element, "author");

        if (authors.contains(author)) {
          result.add(genre);
        }
    }
    return result;
  }
}
