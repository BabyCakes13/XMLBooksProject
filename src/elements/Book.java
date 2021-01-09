package elements;


public class Book implements XMLElement{
  private String id;
  private String title;
  private String author;
  private String genre;

  public Book() {}

  public Book(String id, String title, String author, String genre) {
    super();
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
    public String toString() {
        return "Book [" + this.id + "]: " + this.title +
        ", " + this.author + ", " + this.genre;
    }
}
