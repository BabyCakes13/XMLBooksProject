package main;

import java.util.HashSet;
import java.util.Set;
import org.w3c.dom.Element;

public class Interogation {
  private DOMparser parser;
/**
* Interogation class.
*
* Having been given three parsers for the books,
* writers and genres, the Interogation class is
* used for querying from the XML databases, based
* on some questions.
*/
  public Interogation(DOMparser parser) {
    this.parser = parser;
  }

  public Set<String> getEnglishWriters() {
    System.out.println("\nQuerry for all English writers...");
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("writer")) != null){
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
    System.out.println("\nQuerry for all romance genre books...");
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
    System.out.println("\nQuerry for all Colombian romance books...");
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("writer")) != null){
      Set<String> partialResult = new HashSet<>();
        String nationality =
          this.parser.extractElementFromTag(element, "nationality");
        String writerName =
          this.parser.extractElementFromTag(element, "name");

        if (nationality.equals("Colombian")) {
          partialResult = this.getAllRomanceBooksOfWriter(writerName);
          result.addAll(partialResult);
        }
    }
    return result;
  }

  public Set<String> getAllRomanceBooksOfWriter(String writerName) {
    System.out.println("\nQuerry for all romance book of writer " + writerName + "...");
    Element element = null;
    Set<String> result = new HashSet<>();

    while((element = this.parser.nextElement("book")) != null){
        String writer =
          this.parser.extractElementFromTag(element, "author");
        String title =
          this.parser.extractElementFromTag(element, "title");
        String genre =
          this.parser.extractElementFromTag(element, "genre");

        if ((writer.equals(writerName)) && (genre.equals("Romance"))) {
          result.add(title);
        }
    }
    return result;
  }

  public Set<String> getWritersWithDystopianGenre() {
    System.out.println("\nQuerry for all writers with dystopian books...");
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("book")) != null){
        String genre =
          this.parser.extractElementFromTag(element, "genre");
        String writer =
          this.parser.extractElementFromTag(element, "author");

        if (genre.equals("Dystopian")) {
          result.add(writer);
        }
    }
    return result;
  }

  public Set<String> getEnglishWritersAlive() {
    System.out.println("\nQuerry for all English writers still alive...");
    Set<String> result = new HashSet<>();
    Element element = null;

    while((element = this.parser.nextElement("writer")) != null){
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
    System.out.println("\nQuerry for all English genres...");
    Set<String> result = new HashSet<>();
    Set<String> writers = this.getEnglishWriters();
    Element element = null;

    while((element = this.parser.nextElement("book")) != null){
        String genre =
          this.parser.extractElementFromTag(element, "genre");
        String writer =
          this.parser.extractElementFromTag(element, "author");

        if (writers.contains(writer)) {
          result.add(genre);
        }
    }
    return result;
  }
}
