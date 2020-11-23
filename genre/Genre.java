package genre;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Genre {
  private int id;
  private String name;

  public Genre() {}

  public Genre(int id, String name) {
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
        return "Genre [" + this.id + "]: " + this.name;
    }
}
