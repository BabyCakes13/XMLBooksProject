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
  private String mainTag;
  private Document document;
  private int i;

  public DOMparser() {}

  public DOMparser(File inputXMLFile, String mainTag) {
    super();
    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDOMparser();
    this.mainTag = mainTag;
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

  private NodeList nodes() {
    return this.document.getElementsByTagName(this.mainTag);
  }

  public Node nextNode() {
    NodeList authorNodes = this.nodes();
    Node node = authorNodes.item(this.i++);
    if(node == null) this.i = 0;
    return node;
  }

  public Element nextElement() {
    Node node = this.nextNode();

    while ((node != null) && (node.getNodeType() != Node.ELEMENT_NODE)) {
      node = this.nextNode();
    }

    return (Element) node;
  }

  public String extractElementFromTag(Element element, String tag) {
    return element.getElementsByTagName(tag).item(0).getTextContent();
  }
}
