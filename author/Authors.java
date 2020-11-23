package author;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.ArrayList;

@XmlRootElement(name = "authors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Authors {

  @XmlElement(name = "author")
  private ArrayList<Author> authors;

  public Authors() {}

  public Authors(ArrayList<Author> authors) {
    super();
    this.authors = authors;
  }

  public ArrayList<Author> getAuthors() {
    return this.authors;
  }

  @Override
    public String toString() {
        return "Authors: " + this.authors;
    }
}
