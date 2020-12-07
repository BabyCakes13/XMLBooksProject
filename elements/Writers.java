package elements;

import javax.xml.bind.annotation.XmlElement;

import java.util.ArrayList;

public class Writers {

  @XmlElement(name = "writer")
  private ArrayList<Writer> writers;

  public Writers() {}

  // resulted from JAXB unmarshalling
  public Writers(ArrayList<Writer> writers) {
    super();
    this.writers = writers;
  }

  public ArrayList<Writer> getWriters() {
    return this.writers;
  }

  @Override
  public String toString() {
    return "Writers:\n" + this.writers + "\n";
  }

}
