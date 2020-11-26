import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Set;

import book.Books;
import author.Authors;
import genre.Genres;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {
  // @XmlElement(name = "books")
  private Books books;
  // @XmlElement(name = "authors")
  private Authors authors;
  // @XmlElement(name = "genres")
  private Genres genres;

  public Library() {}

  public Library(Books books, Authors authors, Genres genres) {
    super();
    this.books = books;
    this.authors = authors;
    this.genres = genres;
  }

  public Books getBooks() {
    return this.books;
  }

  public void setBooks(Books books) {
    this.books = books;
  }

  public Authors getAuthors() {
    return this.authors;
  }

  public void setAuthors(Authors authors) {
    this.authors = authors;
  }

  public Genres getGenre() {
    return this.genres;
  }

  public void setGenre(Genres genre) {
    this.genres = genres;
  }

  @Override
  public String toString() {
    return "Library:\n" + this.books + "\n" + this.authors + "\n" + this.genres;
  }
}
