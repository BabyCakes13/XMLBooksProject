package elementsParserXPath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

import elements.Book;
import elements.Genre;
import elements.Writer;
import elements.XMLElement;

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

	public ArrayList<XMLElement> iterateNodesAndApply(String expression, ElementParser ep) {
		ArrayList<XMLElement> querryResults = new ArrayList<>();
		
		try {
			NodeList nodeList = (NodeList) this.xPath.compile(expression).evaluate(this.document,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nodeList.item(i);
					XMLElement parsedObject = ep.parse(el);
					
					if(parsedObject != null) {
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
		ElementParser ep;

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

	public ArrayList<XMLElement> parseBooks(Genre genre) {
		return this.iterateNodesAndApply("library/books/book", new BookParserByGenre(genre));
	}

	public ArrayList<XMLElement> parseBooks(Writer writer) {
		return this.iterateNodesAndApply("library/books/book", new BookParserByAuthor(writer));
	}

	public ArrayList<XMLElement> parseWriters(String element, String elementType) {
		if (elementType.equals("nationality")) {
			return this.iterateNodesAndApply("library/writers/writer", new WriterParserByNationality(element));
		} else if (elementType.equals("name")) {
			return this.iterateNodesAndApply("library/writers/writer", new WriterParserByName(element));
		}
		
		return null;
	}

	public ArrayList<XMLElement> parseWriters(boolean alive) {
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByAlive(alive));
	}

	public ArrayList<XMLElement> parseGenres(String name) {
		return this.iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
	}

	public String getLastButOneElementOf(String expression, String delimiter) {
		String[] splited = expression.split(delimiter);

		if (false) {
			System.out.println("Printing the result of splitting:");
			for (String word : splited) {
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
