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
  private int i;

  public DOMparser() {}

  public DOMparser(File inputXMLFile) {
    super();
    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDOMparser();
    this.i = 0;
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

  private NodeList nodes(String tag) {
    return this.document.getElementsByTagName(tag);
  }

  public Node nextNode(String tag) {
    NodeList authorNodes = this.nodes(tag);
    Node node = authorNodes.item(this.i++);
    if(node == null) this.i = 0;
    return node;
  }

  public Element nextElement(String tag) {
    Node node = this.nextNode(tag);

    while ((node != null) && (node.getNodeType() != Node.ELEMENT_NODE)) {
      node = this.nextNode(tag);
    }

    return (Element) node;
  }

  public String extractElementFromTag(Element element, String tag) {
    return element.getElementsByTagName(tag).item(0).getTextContent();
  }
}
