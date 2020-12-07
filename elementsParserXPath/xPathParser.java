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

	public void tryXPath() {
		this.parseAll("library/books/book");
		this.parseAll("library/writers/writer");
		this.parseAll("library/genres/genre");
		this.parseAll("library/macarons/macaron"); // FAKE FAKE FAKE

		this.parseBooks(new Genre("Romance"));
		this.parseBooks(new Writer("Fyodor Dostoevsky"));

		this.parseWriters("Fyodor Dostoevsky", "name");
		this.parseWriters("English", "nationality");
		this.parseWriters(true);
		this.parseWriters(false);

		this.parseGenres("Romance");
	}

	public void iterateNodesAndApply(String expression, ElementParser ep) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile(expression).evaluate(this.document,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nodeList.item(i);
					ep.parse(el);
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e);
		}
	}

	public void parseAll(String expression) {
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
			return;
		}

		this.iterateNodesAndApply(expression, ep);
	}

	public void parseBooks(Genre genre) {
		System.out.println("\nDisplaying books of genre " + genre.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookParserByGenre(genre));
	}

	public void parseBooks(Writer writer) {
		System.out.println("\nDisplaying books written by " + writer.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookParserByAuthor(writer));
	}

	public void parseWriters(String element, String elementType) {
		if (elementType.equals("nationality")) {
			System.out.println("\nDisplaying writers of " + elementType + " " + element + "...");
			iterateNodesAndApply("library/writers/writer", new WriterParserByNationality(element));
		} else if (elementType.equals("name")) {
			System.out.println("\nDisplaying author of " + elementType + " " + element + "...");
			iterateNodesAndApply("library/writers/writer", new WriterParserByName(element));
		}
	}

	public void parseWriters(boolean alive) {
		if (alive) {
			System.out.println("\nDisplaying alive authors...");
		} else {
			System.out.println("\nDisplaying dead authors...");
		}
		iterateNodesAndApply("library/writers/writer", new WriterParserByAlive(alive));
	}

	public void parseGenres(String name) {
		System.out.println("\nDisplaying " + name + " genre...");
		iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
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
