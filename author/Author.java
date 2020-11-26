package author;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Author {
  private String id;
  private String name;
  private String birthYear;
  private String deathYear;
  private String nationality;

  public Author() {}

  public Author(String id, String name, String birthYear, String deathYear, String nationality) {
    super();
    this.id = id;
    this.name = name;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
    this.nationality = nationality;
  }

  @XmlAttribute
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @XmlElement
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public String getBirthYear() {
    return this.birthYear;
  }

  public void setBirthYear(String birthYear) {
    this.birthYear = birthYear;
  }

  @XmlElement
  public String getDeathYear() {
    return this.deathYear;
  }

  public void setDeathYear(String deathYear) {
    this.deathYear = deathYear;
  }

  @XmlElement
  public String getNationality() {
    return this.nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
    public String toString() {
        return "Author [" + this.id + "]: " +
                            this.name + " (" +
                            this.birthYear + " - " +
                            this.deathYear + "), " +
                            this.nationality + "\n";
    }
}
