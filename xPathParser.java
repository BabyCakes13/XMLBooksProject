

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

import genre.Genre;
import writer.Writer;

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
			NodeList nodeList = (NodeList) this.xPath.compile(expression)
					.evaluate(this.document, XPathConstants.NODESET);

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

		switch(displayItem) {
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

	public interface ElementParser {
		public void parse(Element el);
	}

	// BOOK PRINTERS STARTING HERE
	
	public class BookParser implements ElementParser {
		public void parse(Element el) {
			String title = el.getElementsByTagName("title").item(0).getTextContent();
			String author = el.getElementsByTagName("author").item(0).getTextContent();
			String genre = el.getElementsByTagName("genre").item(0).getTextContent();

			System.out.println("Book: " + title + " (" + author + ", " + genre + ")");
		}
	}

	public class BookParserByGenre extends BookParser {
		public Genre genre;
		public BookParserByGenre(Genre genre) {
			this.genre = genre;
		}

		public void parse(Element el) {
			String bookGenre = el.getElementsByTagName("genre").item(0).getTextContent();
			if (bookGenre.equals(this.genre.getName()))
				super.parse(el);
		}
	}

	public class BookParserByAuthor extends BookParser {
		public Writer writer;
		public BookParserByAuthor(Writer writer) {
			this.writer = writer;
		}

		public void parse(Element el) {
			String bookAuthor = el.getElementsByTagName("author").item(0).getTextContent();
			if (bookAuthor.equals(this.writer.getName()))
				super.parse(el);
		}
	}

	public void parseBooks(Genre genre) {
		System.out.println("\nDisplaying books of genre " + genre.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookParserByGenre(genre));
	}

	public void parseBooks(Writer writer) {
		System.out.println("\nDisplaying books written by " + writer.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookParserByAuthor(writer));
	}

	// WRITER PRINTERS STARTING HERE

	public class WriterParser implements ElementParser {
		public void parse(Element el) {
			String name = el.getElementsByTagName("name").item(0).getTextContent();
			String birthYear = el.getElementsByTagName("birthYear").item(0).getTextContent();
			String deathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
			String nationality = el.getElementsByTagName("nationality").item(0).getTextContent();

			System.out.println("Writer: " + name + " (" +
					birthYear + ", " +
					deathYear + ", " +
					nationality + ")");
		}
	}

	public class WriterParserByName extends WriterParser {
		public String name;
		public WriterParserByName(String name) {
			this.name = name;
		}

		public void parse(Element el) {
			String writerName = el.getElementsByTagName("name").item(0).getTextContent();
			if (writerName.equals(this.name))
				super.parse(el);
		}
	}
	
	public class WriterParserByNationality extends WriterParser {
		public String nationality;
		public WriterParserByNationality(String nationality) {
			this.nationality = nationality;
		}

		public void parse(Element el) {
			String writerNationality = el.getElementsByTagName("nationality").item(0).getTextContent();
			if (writerNationality.equals(this.nationality))
				super.parse(el);
		}
	}

	public class WriterParserByAlive extends WriterParser {
		private boolean alive;
		public WriterParserByAlive(boolean alive) {
			this.alive = alive;
		}

		public void parse(Element el) {
			String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
			if (writerDeathYear.equals("-") && (this.alive))
				super.parse(el);
			if (!writerDeathYear.equals("-") && (!this.alive))
				super.parse(el);
		}
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

	public class GenreParser implements ElementParser {
		public void parse(Element el) {
			String name = el.getElementsByTagName("name").item(0).getTextContent();

			System.out.println("Genre: " + name);
		}
	}
	
	public class GenreParserByName extends GenreParser {
		public String name;
		public GenreParserByName(String name) {
			this.name = name;
		}

		public void parse(Element el) {
			String genreName = el.getElementsByTagName("name").item(0).getTextContent();
			if (genreName.equals(this.name))
				super.parse(el);
		}
	}
	
	public void parseGenres(String name) {
		System.out.println("\nDisplaying " + name + " genre...");
		iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
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
