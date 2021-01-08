package elementsParserXPath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

import elements.Author;
import elements.Book;
import elements.Genre;
import elements.Id;
import elements.Title;
import elements.Writer;
import elements.XMLElement;
import elementsParserXPath.operations.bookOperations.BookDeleter;
import elementsParserXPath.operations.bookOperations.BookDeleterByAuthor;
import elementsParserXPath.operations.bookOperations.BookDeleterByGenre;
import elementsParserXPath.operations.bookOperations.BookDeleterById;
import elementsParserXPath.operations.bookOperations.BookDeleterByTitle;
import elementsParserXPath.operations.bookOperations.BookParser;
import elementsParserXPath.operations.bookOperations.BookParserByAuthor;
import elementsParserXPath.operations.bookOperations.BookParserByGenre;
import elementsParserXPath.operations.genreOperations.GenreDeleter;
import elementsParserXPath.operations.genreOperations.GenreParser;
import elementsParserXPath.operations.genreOperations.GenreParserByName;
import elementsParserXPath.operations.writerOperations.WriterDeleter;
import elementsParserXPath.operations.writerOperations.WriterParser;
import elementsParserXPath.operations.writerOperations.WriterParserByAlive;
import elementsParserXPath.operations.writerOperations.WriterParserByName;
import elementsParserXPath.operations.writerOperations.WriterParserByNationality;

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
	
	protected void updateDocument() {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			t.transform(new DOMSource(document), new StreamResult(System.out));
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
}
