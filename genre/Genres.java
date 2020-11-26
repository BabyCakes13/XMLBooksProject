package genre;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.ArrayList;

public class Genres {

  @XmlElement(name = "genre")
  private ArrayList<Genre> genre;

  public Genres() {}

  public Genres(ArrayList<Genre> genre) {
    super();
    this.genre = genre;
  }

  public ArrayList<Genre> getGenres() {
    return this.genre;
  }

  public void setGenres(ArrayList<Genre> genre) {
    this.genre = genre;
  }

  @Override
    public String toString() {
        return "Genres:\n" + this.genre + "\n";
    }
}
