import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

import book.Book;
import author.Author;
import genre.Genre;

public class DOMparser {
  private File inputXMLFile;
  private Document document;

  public DOMparser() {}

  public DOMparser(File inputXMLFile) {
    super();
    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDOMparser();
  }

  private Document setupDOMparser() {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(this.inputXMLFile);
      doc.getDocumentElement().normalize();

      System.out.println("Root element:" + doc.getDocumentElement().getNodeName());

      return doc;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public NodeList nodes(String tag) {
    return this.document.getElementsByTagName(tag);
  }
}
