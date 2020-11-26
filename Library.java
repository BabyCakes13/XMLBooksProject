import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Set;

import book.Book;
import author.Author;
import genre.Genres;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {
  // @XmlElement(name = "books")
  private Set<Book> books;
  // @XmlElement(name = "authors")
  private Set<Author> authors;
  // @XmlElement(name = "genres")
  private Genres genres;

  public Library() {}

  public Library(Set<Book> books, Set<Author> authors, Genres genres) {
    super();
    this.books = books;
    this.authors = authors;
    this.genres = genres;
  }

  public Set<Book> getBooks() {
    return this.books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  public Set<Author> getAuthors() {
    return this.authors;
  }

  public void setAuthors(Set<Author> authors) {
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
