

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
		this.displayAll("library/books/book");
		this.displayAll("library/writers/writer");
		this.displayAll("library/genres/genre");
		this.displayAll("library/macarons/macaron"); // FAKE FAKE FAKE

		this.displayBooks(new Genre("Romance"));
		this.displayBooks(new Writer("Fyodor Dostoevsky"));

		this.displayWriters("Fyodor Dostoevsky", "name");
		this.displayWriters("English", "nationality");
		this.displayWriters(true);
		this.displayWriters(false);
	}


	public void iterateNodesAndApply(String expression, ElementPrinter ep) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile(expression)
					.evaluate(this.document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nodeList.item(i);
					ep.display(el);
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e);
		}
	}

	public void displayAll(String expression) {
		String displayItem = this.getLastButOneElementOf(expression, "/");
		System.out.println("\nDisplaying all " + displayItem + " from the library...");
		ElementPrinter ep;

		switch(displayItem) {
		case "books":
			ep = new BookPrinter();
			break;
		case "writers":
			ep = new WriterPrinter();
			break;
		case "genres":
			ep = new GenrePrinter();
			break;
		default:
			System.out.println("The parsing option " + displayItem + " does not exist.");
			return;
		}

		this.iterateNodesAndApply(expression, ep);
	}

	public interface ElementPrinter {
		public void display(Element el);
	}

	// BOOK PRINTERS STARTING HERE
	public class BookPrinter implements ElementPrinter {
		public void display(Element el) {
			String title = el.getElementsByTagName("title").item(0).getTextContent();
			String author = el.getElementsByTagName("author").item(0).getTextContent();
			String genre = el.getElementsByTagName("genre").item(0).getTextContent();

			System.out.println("Book: " + title + " (" + author + ", " + genre + ")");
		}
	}

	public class BookPrinterByGenre extends BookPrinter {
		public Genre genre;
		public BookPrinterByGenre(Genre genre) {
			this.genre = genre;
		}

		public void display(Element el) {
			String bookGenre = el.getElementsByTagName("genre").item(0).getTextContent();
			if (bookGenre.equals(this.genre.getName()))
				super.display(el);
		}
	}

	public class BookPrinterByAuthor extends BookPrinter {
		public Writer writer;
		public BookPrinterByAuthor(Writer writer) {
			this.writer = writer;
		}

		public void display(Element el) {
			String bookAuthor = el.getElementsByTagName("author").item(0).getTextContent();
			if (bookAuthor.equals(this.writer.getName()))
				super.display(el);
		}
	}

	public void displayBooks(Genre genre) {
		System.out.println("\nDisplaying books of genre " + genre.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookPrinterByGenre(genre));
	}

	public void displayBooks(Writer writer) {
		System.out.println("\nDisplaying books written by " + writer.getName() + "...");
		iterateNodesAndApply("library/books/book", new BookPrinterByAuthor(writer));
	}

	// WRITER PRINTERS STARTING HERE

	public class WriterPrinter implements ElementPrinter {
		public void display(Element el) {
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

	public class WriterPrinterByName extends WriterPrinter {
		public String name;
		public WriterPrinterByName(String name) {
			this.name = name;
		}

		public void display(Element el) {
			String writerName = el.getElementsByTagName("name").item(0).getTextContent();
			if (writerName.equals(this.name))
				super.display(el);
		}
	}
	
	public class WriterPrinterByNationality extends WriterPrinter {
		public String nationality;
		public WriterPrinterByNationality(String nationality) {
			this.nationality = nationality;
		}

		public void display(Element el) {
			String writerNationality = el.getElementsByTagName("nationality").item(0).getTextContent();
			if (writerNationality.equals(this.nationality))
				super.display(el);
		}
	}

	public class WriterPrinterByAlive extends WriterPrinter {
		private boolean alive;
		public WriterPrinterByAlive(boolean alive) {
			this.alive = alive;
		}

		public void display(Element el) {
			String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
			if (writerDeathYear.equals("-") && (this.alive))
				super.display(el);
			if (!writerDeathYear.equals("-") && (!this.alive))
				super.display(el);
		}
	}

	public void displayWriters(String element, String elementType) {
		if (elementType.equals("nationality")) {
			System.out.println("\nDisplaying writers of " + elementType + " " + element + "...");
			iterateNodesAndApply("library/writers/writer", new WriterPrinterByNationality(element));
		} else if (elementType.equals("name")) {
			System.out.println("\nDisplaying author of " + elementType + " " + element + "...");
			iterateNodesAndApply("library/writers/writer", new WriterPrinterByName(element));
		}
	}

	public void displayWriters(boolean alive) {
		if (alive) {
			System.out.println("\nDisplaying alive authors...");
		} else {
			System.out.println("\nDisplaying dead authors...");
		}
		iterateNodesAndApply("library/writers/writer", new WriterPrinterByAlive(alive));
	}

	public class GenrePrinter implements ElementPrinter {
		public void display(Element el) {
			String name = el.getElementsByTagName("name").item(0).getTextContent();

			System.out.println("Genre: " + name);
		}
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
