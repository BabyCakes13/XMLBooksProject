import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import elements.Book;
import elements.Genre;
import elements.Writer;

/**
* Parser for an XML file.
*
* Class which will be used for parsing over the
* XML databases, and extracting information about
* its elements.
*/
public class DOMparser {
  private File inputXMLFile;
  private Document document;
  private NodeList library;
  private Map<String, Integer> iterators;

  public DOMparser() {}

  public DOMparser(File inputXMLFile) {
    super();
    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDOMparser();
    this.library = this.getLibrary();

    this.iterators  = new HashMap<String, Integer>();
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

      // System.out.println("Root element is:" + doc.getDocumentElement().getNodeName());

      return doc;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private NodeList getLibrary() {
    return this.document.getElementsByTagName("library");
  }

  private NodeList nodes(String nodeName) {
    NodeList nodes = this.document.getElementsByTagName(nodeName + "s");
    NodeList subNodes = ((Element)nodes.item(0)).getElementsByTagName(nodeName);

    if (false) {
      for(int i = 0; i < subNodes.getLength(); i++) {
        System.out.println("..." + subNodes.item(i).getTextContent());
      }
      System.out.println("Done.");
    }

    return subNodes;
  }

  public Node nextNode(String nodeName) {
    NodeList nodes = this.nodes(nodeName);
    int iterator = this.iterators.getOrDefault(nodeName, 0);
    Node node = nodes.item(iterator);
    
    this.iterators.put(nodeName, iterator + 1);

    if(node == null)
      this.iterators.put(nodeName, 0);
    return node;
  }

  /**
  * Element is a subclass of Node. In the parser,
  * we only want to work with elements for now.
  */
  public Element nextElement(String nodeName) {
    Node node = null;
    while (((node = this.nextNode(nodeName)) != null) &&
           (node.getNodeType() != Node.ELEMENT_NODE)) {}
    return (Element) node;
  }

  public String extractElementFromTag(Element element, String tag) {
    return element.getElementsByTagName(tag).item(0).getTextContent();
  }
}
