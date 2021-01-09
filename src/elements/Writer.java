package elements;


public class Writer implements XMLElement{
  private String id;
  private String name;
  private String birthYear;
  private String deathYear;
  private String nationality;

  public Writer() {}
  
  public Writer(String name) {
	  this.name = name;
  }

  public Writer(String id, String name, String birthYear, String deathYear, String nationality) {
    super();
    this.id = id;
    this.name = name;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
    this.nationality = nationality;
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

  public String getBirthYear() {
    return this.birthYear;
  }

  public void setBirthYear(String birthYear) {
    this.birthYear = birthYear;
  }

  public String getDeathYear() {
    return this.deathYear;
  }

  public void setDeathYear(String deathYear) {
    this.deathYear = deathYear;
  }

  public String getNationality() {
    return this.nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
    public String toString() {
        return "Writer [" + this.id + "]: " +
                            this.name + " (" +
                            this.birthYear + " - " +
                            this.deathYear + "), " +
                            this.nationality;
    }
}
