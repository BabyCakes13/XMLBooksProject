import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

public class Book {
  private int id;
  private String title;
  private String author;
  private String genre;

  public Book() {}

  public Book(int id, String title, String author, String genre) {
    super();
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  @XmlAttribute
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @XmlElement
  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @XmlElement
  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
    public String toString() {
        return "Book [" + this.id + "]: " + this.title +
        ", " + this.author + ", " + this.genre;
    }
}
