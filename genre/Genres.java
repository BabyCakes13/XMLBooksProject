package genre;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.ArrayList;

@XmlRootElement(name = "genres")
@XmlAccessorType(XmlAccessType.FIELD)
public class Genres {

  @XmlElement(name = "genre")
  private ArrayList<Genre> genres;

  public Genres() {}

  public Genres(ArrayList<Genre> genres) {
    super();
    this.genres = genres;
  }

  public ArrayList<Genre> getGenres() {
    return this.genres;
  }

  public void setGenres(ArrayList<Genre> genres) {
    this.genres = genres;
  }

  @Override
    public String toString() {
        return "Genres: " + this.genres;
    }
}
