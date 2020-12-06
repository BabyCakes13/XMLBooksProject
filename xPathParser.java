import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

public class xPathParser {
  private File inputXMLFile;
  private Document document;
  private XPath xPath;

  public xPathParser(File inputXMLFile) {
    System.out.println("CREATING XPATH PARSER...");

    this.inputXMLFile = inputXMLFile;
    this.document = this.setupDocument();
    this.xPath = XPathFactory.newInstance().newXPath();
  }

  private Document setupDocument() {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(this.inputXMLFile);
      doc.getDocumentElement().normalize();

      return doc;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void tryXPath() {
    this.displayAll("library/books/book");
    this.displayAll("library/writers/writer");
    this.displayAll("library/genres/genre");
  }

  public void displayAll(String expression) {
    try {
      NodeList nodeList = (NodeList) this.xPath.compile(expression)
        .evaluate(this.document, XPathConstants.NODESET);

      for (int i = 0; i < nodeList.getLength(); i++) {
          if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
              Element el = (Element) nodeList.item(i);
              String element_name = el.getNodeName();

              switch(element_name) {
                case "book":
                  this.displayBookInfo(el);
                  break;
                case "writer":
                  this.displayWriterInfo(el);
                  break;
                case "genre":
                  this.displayGenreInfo(el);
                  break;
                default:
                  System.out.println("The parsing option " + element_name + " does not exist.");
              }
          }
      }
    } catch (XPathExpressionException e) {
      System.out.println(e);
    }
  }

  public void displayBookInfo(Element el) {
    String title = el.getElementsByTagName("title").item(0).getTextContent();
    String author = el.getElementsByTagName("author").item(0).getTextContent();
    String genre = el.getElementsByTagName("genre").item(0).getTextContent();

    System.out.println("Book: " + title + " (" + author + ", " + genre + ")");
  }

  public void displayWriterInfo(Element el) {
    String name = el.getElementsByTagName("name").item(0).getTextContent();
    String birthYear = el.getElementsByTagName("birthYear").item(0).getTextContent();
    String deathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
    String nationality = el.getElementsByTagName("nationality").item(0).getTextContent();

    System.out.println("Writer: " + name + " (" +
                                    birthYear + ", " +
                                    deathYear + ", " +
                                    nationality + ")");
    }

    public void displayGenreInfo(Element el) {
      String name = el.getElementsByTagName("name").item(0).getTextContent();

      System.out.println("Genre: " + name);
    }

  public String getLastButOneElementOf(String expression, String delimiter) {
    String[] splited = expression.split(delimiter);

    if(false) {
      System.out.println("Printing the result of splitting:");
      for (String word: splited) {
        System.out.println(word + " ");
      }
    }

    try {
      String lastElement = splited[splited.length - 2];
      return lastElement;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }

    return null;
  }
}
