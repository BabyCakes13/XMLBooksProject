import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.ArrayList;

import book.Books;
import genre.Genres;
import writer.Writers;

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

  public Genres getGenre() {
    return this.genres;
  }

  public void setGenre(Genres genre) {
    this.genres = genres;
  }

  @Override
  public String toString() {
    return "Library:\n" + this.books + "\n" + this.writers + "\n" + this.genres;
  }
}
