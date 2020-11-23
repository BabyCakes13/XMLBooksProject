package author;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

public class Author {
  private int id;
  private String name;

  public Author() {}

  public Author(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  @XmlAttribute
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
    public String toString() {
        return "Author [" + this.id + "]: " + this.name;
    }
}
