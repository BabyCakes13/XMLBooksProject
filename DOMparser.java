import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;

import book.Book;
import author.Author;
import genre.Genre;

/**
* Parser for an XML file.
*
* Class which will be used for parsing over the
* XML databases, and extracting information about
* its elements.
*/
public class DOMparser {
  private File inputXMLFile;
  private String mainTag;
  private Document document;
  private int i;

  public DOMparser() {}

  /**
  * Constructor for the DOMparser class.
  *
  * The constructor is given the input XML file
  * and the main tag it will search for in the XML.
  *
  * For example, if we have the following structure:
  * <?xml version = "1.0"?>
  * <books>
  *  <book id="1">
  *    <title>Notes from Underground</title>
  *    <author>Fyodor Dostoevsky</author>
  *    <genre>Philosophical</genre>
  *  </book>
  *</books>
  * The mainTag would be "book".
  */
  public DOMparser(File inputXMLFile, String mainTag) {
    super();
    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDOMparser();
    this.mainTag = mainTag;
    this.i = 0;
  }

  /**
  * Setup the DOM parser and normalize the document.
  *
  * @return The parsed document through DOM.
  */
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

  /**
  * @return The nodes for the mainTag parameter.
  *
  * For example, if we have the following structure:
  * <?xml version = "1.0"?>
  * <books>
  *  <book id="1">
  *    <title>Notes from Underground</title>
  *    <author>Fyodor Dostoevsky</author>
  *    <genre>Philosophical</genre>
  *  </book>
  *</books>
  * The method will return: title, author and genre elements.
  */
  private NodeList nodes() {
    return this.document.getElementsByTagName(this.mainTag);
  }

  public Node nextNode() {
    NodeList authorNodes = this.nodes();
    Node node = authorNodes.item(this.i++);
    if(node == null) this.i = 0;
    return node;
  }

  /**
  * Element is a subclass of Node. In the parser,
  * we only want to work with elements for now.
  */
  public Element nextElement() {
    Node node = null;
    while (((node = this.nextNode()) != null) &&
           (node.getNodeType() != Node.ELEMENT_NODE)) {}
    return (Element) node;
  }

  public String extractElementFromTag(Element element, String tag) {
    return element.getElementsByTagName(tag).item(0).getTextContent();
  }
}
