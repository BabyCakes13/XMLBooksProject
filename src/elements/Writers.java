package elements;

import javax.xml.bind.annotation.XmlElement;

import java.util.ArrayList;

public class Writers {

  @XmlElement(name = "writer")
  private ArrayList<Writer> writers;

  public Writers() {}

  public Writers(ArrayList<Writer> writers) {
    super();
    this.writers = writers;
  }

  public ArrayList<Writer> getWriters() {
    return this.writers;
  }

  @Override
  public String toString() {
		String toString = "";
		
		for (Writer writer : this.writers) {
			toString = toString + writer + "\n";
		}
		
		return toString;
	}

}
