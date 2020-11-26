package book;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Set;

public class Books {

  @XmlElement(name = "book")
  private Set<Book> books;

  public Books() {}

  public Books(Set<Book> books) {
    super();
    this.books = books;
  }

  public Set<Book> getBooks() {
    return this.books;
  }

  @Override
    public String toString() {
        return "Books:\n" + this.books + "\n";
    }
}
