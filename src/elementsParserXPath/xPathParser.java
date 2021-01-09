package elementsParserXPath;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;

import elements.XMLElement;
import elementsParserXPath.operations.bookOperations.BookDeleter;
import elementsParserXPath.operations.bookOperations.BookParser;
import elementsParserXPath.operations.genreOperations.GenreDeleter;
import elementsParserXPath.operations.genreOperations.GenreParser;
import elementsParserXPath.operations.writerOperations.WriterDeleter;
import elementsParserXPath.operations.writerOperations.WriterParser;

public class xPathParser {
	protected Document document;
	protected XPath xPath;

	public xPathParser(Document xmlDocument) {
		this.document = xmlDocument;
		this.xPath = XPathFactory.newInstance().newXPath();
	}

	public ArrayList<XMLElement> iterateNodesAndApply(String expression, ElementOperation ep) {
		ArrayList<XMLElement> querryResults = new ArrayList<>();

		try {
			NodeList nodeList = (NodeList) this.xPath.compile(expression).evaluate(this.document,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nodeList.item(i);
					XMLElement parsedObject = ep.elementOperation(el);

					if (parsedObject != null) {
						querryResults.add(parsedObject);
					}

				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e);
		}

		return querryResults;
	}

	public ArrayList<XMLElement> parseAll(String expression) {
		String displayItem = this.getLastButOneElementOf(expression, "/");
		System.out.println("\nDisplaying all " + displayItem + " from the library...");
		ElementOperation ep;

		switch (displayItem) {
		case "books":
			ep = new BookParser();
			break;
		case "writers":
			ep = new WriterParser();
			break;
		case "genres":
			ep = new GenreParser();
			break;
		default:
			System.out.println("The parsing option " + displayItem + " does not exist.");
			return null;
		}

		return this.iterateNodesAndApply(expression, ep);
	}

	public ArrayList<XMLElement> deleteAll(String expression) {
		String displayItem = this.getLastButOneElementOf(expression, "/");
		System.out.println("Deleting all " + displayItem + " from the library...");
		ElementOperation ep;

		switch (displayItem) {
		case "books":
			ep = new BookDeleter();
			break;
		case "writers":
			ep = new WriterDeleter();
			break;
		case "genres":
			ep = new GenreDeleter();
			break;
		default:
			System.out.println("The parsing option " + displayItem + " does not exist.");
			return null;
		}

		this.iterateNodesAndApply(expression, ep);
		this.updateDocument();
		return null;
	}
	
	public void updateDocument() {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			t.transform(new DOMSource(this.document), new StreamResult("new_books.xml"));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastButOneElementOf(String expression, String delimiter) {
		String[] splited = expression.split(delimiter);

		try {
			String lastElement = splited[splited.length - 2];
			return lastElement;
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}

		return null;
	}
	
	protected void appendChild(Node parentNode, String name, String textContent) {
		Element element = document.createElement(name);
		element.setTextContent(textContent);
		parentNode.appendChild(element);
	}
}
