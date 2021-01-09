package elements;


public class Genre implements XMLElement{
  private String id;
  private String name;

  public Genre() {}

  public Genre(String id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public Genre(String name) {
    super();
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
