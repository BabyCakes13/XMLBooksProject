package genre;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Set;

public class Genres {

  @XmlElement(name = "genre")
  private Set<Genre> genre;

  public Genres() {}

  public Genres(Set<Genre> genre) {
    super();
    this.genre = genre;
  }

  public Set<Genre> getGenres() {
    return this.genre;
  }

  public void setGenres(Set<Genre> genre) {
    this.genre = genre;
  }

  @Override
    public String toString() {
        return "Genres:\n" + this.genre + "\n";
    }
}
