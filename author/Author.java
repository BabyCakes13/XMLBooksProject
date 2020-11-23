package author;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

public class Author {
  private int id;
  private String name;
  private int birthYear;
  private int deathYear;
  private String nationality;

  public Author() {}

  public Author(int id, String name, int birthYear, int deathYear, String nationality) {
    super();
    this.id = id;
    this.name = name;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
    this.nationality = nationality;
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

  @XmlElement
  public int getBirthYear() {
    return this.birthYear;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }

  @XmlElement
  public int getDeathYear() {
    return this.deathYear;
  }

  public void setDeathYear(int deathYear) {
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
                            this.nationality;
    }
}
