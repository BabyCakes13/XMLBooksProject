import javax.xml.bind.annotation.XmlRootElement;

import elements.Books;
import elements.Genres;
import elements.Writers;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {
  // @XmlElement(name = "books")
  private Books books;
  // @XmlElement(name = "writers")
  private Writers writers;
  // @XmlElement(name = "genres")
  private Genres genres;

  public Library() {}

  public Library(Books books, Writers writers, Genres genres) {
    super();
    this.books = books;
    this.writers = writers;
    this.genres = genres;
  }

  public Books getBooks() {
    return this.books;
  }

  public void setBooks(Books books) {
    this.books = books;
  }

  public Writers getWriters() {
    return this.writers;
  }

  public void setWriters(Writers writers) {
    this.writers = writers;
  }

  public Genres getGenres() {
    return this.genres;
  }

  public void setGenres(Genres genres) {
    this.genres = genres;
  }

  @Override
  public String toString() {
    return "Library:\n" + this.books + "\n" + this.writers + "\n" + this.genres;
  }
}
