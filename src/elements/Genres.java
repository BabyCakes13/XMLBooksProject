package elements;

import java.util.ArrayList;

public class Genres {

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
		String toString = "";
		
		for (Genre genre : this.genre) {
			toString = toString + genre + "\n";
		}
		
		return toString;
	}
}
