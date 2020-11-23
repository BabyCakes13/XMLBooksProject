import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import java.util.ArrayList;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
public class Books {

  @XmlElement(name = "book")
  private ArrayList<Book> books;

  public Books() {}

  public Books(ArrayList<Book> books) {
    super();
    this.books = books;
  }

  public ArrayList<Book> getBooks() {
    return this.books;
  }

  @Override
    public String toString() {
        return "Books [" + this.books + "]";
    }
}
