package author;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Set;

public class Authors {

  @XmlElement(name = "author")
  private Set<Author> authors;

  public Authors() {}

  // resulted from JAXB unmarshalling
  public Authors(Set<Author> authors) {
    super();
    this.authors = authors;
  }

  public Set<Author> getAuthors() {
    return this.authors;
  }

  @Override
  public String toString() {
    return "Authors: " + this.authors;
  }

}
