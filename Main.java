import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

import elements.Author;
import elements.Book;
import elements.Genre;
import elements.Id;
import elements.Nationality;
import elements.Title;
import elements.Writer;
import elements.WriterName;
import elements.XMLElement;
import elementsParserXPath.xPathParser;
import elementsParserXPath.xPathParserBook;
import elementsParserXPath.xPathParserGenre;
import elementsParserXPath.xPathParserWriter;

public class Main {
	public static void main(String[] args) {
		System.out.println("Starting the application.\n");

		if (!validateXMLSchema("library.xsd", "library.xml")) {
			System.out.println("The XML schema is not valid. Aborting mission.");
		} else {
			System.out.println("The XML schema is valid.");
		}

		File xmlInputFile = new File("library.xml");
		loadLibrary();
//		solveWithDOM(xmlInputFile);
//		solveWithXPath(xmlInputFile);

//		deleteWithXPath(xmlInputFile);
//		editWithXPath(xmlInputFile);
		addWithXPath(xmlInputFile);
	}
	
	public static void addWithXPath(File xmlInputFile) {
		xPathParserBook xPathBook = new xPathParserBook(xmlInputFile);
		Book newBook = new Book("newBook", "Brand New Book", "Min", "Totally Fiction");
		xPathBook.addBook(newBook);
	}
	public static void solveWithXPath(File xmlInputFile) {
		xPathParserBook xPathBook = new xPathParserBook(xmlInputFile);
		xPathParserWriter xPathWriter = new xPathParserWriter(xmlInputFile);
		xPathParserGenre xPathGenre = new xPathParserGenre(xmlInputFile);

		oneFilterXPathQuerries(xPathBook, xPathWriter, xPathGenre);
		twoFilterXPathQuerries(xPathBook, xPathWriter, xPathGenre);
		threeFilterXPathQuerries(xPathBook, xPathWriter, xPathGenre);
	}

	public static void deleteWithXPath(File xmlInputFile) {
		xPathParserBook xPathBook = new xPathParserBook(xmlInputFile);
		xPathParserWriter xPathWriter = new xPathParserWriter(xmlInputFile);
		xPathParserGenre xPathGenre = new xPathParserGenre(xmlInputFile);

		oneFilterDelete(xPathBook, xPathWriter, xPathGenre);
		twoFilterDelete(xPathBook, xPathWriter, xPathGenre);
	}
	
	public static void editWithXPath(File xmlInputFile) {
		xPathParserBook xPathBook = new xPathParserBook(xmlInputFile);
		xPathParserWriter xPathWriter = new xPathParserWriter(xmlInputFile);
		xPathParserGenre xPathGenre = new xPathParserGenre(xmlInputFile);

		twoFilterEdit(xPathBook, xPathWriter, xPathGenre);
	}
	
	public static void twoFilterEdit(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		xPathGenre.editGenres("Romance", "New Romance");
		xPathGenre.editGenres(new Id("g5"), new Id("newG5"));
		
		xPathBook.editBooks(new Title("Love in the Time of Cholera"), new Title("Love in the Time of Covid"));
		xPathBook.editBooks(new Author("Aldous Huxley"), new Author("Not Aldous Huxley"));
		xPathBook.editBooks(new Genre("Romance"), new Genre("Totally Not Romance"));
		xPathBook.editBooks(new Id("b1"), new Id("NewB1"));
		
		xPathWriter.editWriter(new Author("Aldous Huxley"),new Author("Not Aldous Huxley"));
		xPathWriter.editWriter(new Nationality("Colombian"), new Nationality("Ethiopian"));
		xPathWriter.editWriter(new Id("a3"), new Id("NewA3"));
	}

	public static void oneFilterDelete(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		xPathBook.deleteAll("library/books/book");
		xPathWriter.deleteAll("library/writers/writer");
		xPathGenre.deleteAll("library/genres/genre");
	}

	public static void twoFilterDelete(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		xPathBook.deleteBooks(new Genre("Romance"));
		xPathBook.deleteBooks(new Title("Brave New World"));
		xPathBook.deleteBooks(new Id("b4"));
		xPathBook.deleteBooks(new Author("George Orwell"));

		xPathGenre.deleteGenres("Romance");
		xPathGenre.deleteGenres(new Id("g1"));

		xPathWriter.deleteWriters(new Id("a1"));
		xPathWriter.deleteWriters(new Nationality("Colombian"));
		xPathWriter.deleteWriters("alive");
		xPathWriter.deleteWriters("dead");
		xPathWriter.deleteWriters(new WriterName("Aldous Huxley"));
	}

	public static void oneFilterXPathQuerries(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		ArrayList<XMLElement> allBooks = xPathBook.parseAll("library/books/book");
		ArrayList<XMLElement> allWriters = xPathWriter.parseAll("library/writers/writer");
		ArrayList<XMLElement> allGenres = xPathGenre.parseAll("library/genres/genre");

		System.out.println("\nXPath querry to list all the books from the library...");
		print(allBooks);
		System.out.println("\nXPath querry to list all the writers from the library...");
		print(allWriters);
		System.out.println("\nXPath querry to list all the genres from the library...");
		print(allGenres);
	}

	public static void twoFilterXPathQuerries(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		ArrayList<XMLElement> romanceBooks = xPathBook.parseBooks(new Genre("Romance"));
		ArrayList<XMLElement> fyodorWriter = xPathBook.parseBooks(new Writer("Fyodor Dostoevsky"));

		ArrayList<XMLElement> fyodorBooks = xPathWriter.parseWriters("Fyodor Dostoevsky", "name");
		ArrayList<XMLElement> englishWriters = xPathWriter.parseWriters("English", "nationality");
		ArrayList<XMLElement> aliveWriters = xPathWriter.parseWriters(true);
		ArrayList<XMLElement> deadAuthors = xPathWriter.parseWriters(false);

		ArrayList<XMLElement> romanceGenre = xPathGenre.parseGenres("Romance");

		System.out.println("\nXPath querry to list all the romance books...");
		print(romanceBooks);
		System.out.println("\nXPath querry to list all information about Fyodor Dostoevski...");
		print(fyodorWriter);
		System.out.println("\nXPath querry to list all books written by Fyodor Dostoevski...");
		print(fyodorBooks);
		System.out.println("\nXPath querry to list all the English writers...");
		print(englishWriters);
		System.out.println("\nXPath querry to list all alive writers...");
		print(aliveWriters);
		System.out.println("\nXPath querry to list all dead writers...");
		print(deadAuthors);
		System.out.println("\nXPath querry to list information about the romance genre...");
		print(romanceGenre);
	}

	public static void threeFilterXPathQuerries(xPathParserBook xPathBook, xPathParserWriter xPathWriter,
			xPathParserGenre xPathGenre) {
		ArrayList<XMLElement> gabrielRealism = xPathBook.parseBooks(new Genre("Realism"),
				new Writer("Gabriel García Márquez"));
		ArrayList<XMLElement> fyodorPhilosophicalBooks = xPathBook.parseBooks(new Genre("Philosophical"),
				new Writer("Fyodor Dostoevsky"));

		ArrayList<XMLElement> colombianRomanceBooks = xPathBook.parseBooks(new Genre("Romance"), "Colombian");
		ArrayList<XMLElement> englishDystopianBooks = xPathBook.parseBooks(new Genre("Dystopian"), "English");

		System.out.println("\nXPath querry to list all the realism books of Gabriel Garcia Marqez...");
		print(gabrielRealism);
		System.out.println("\nXPath querry to list all the philosophical books of Fyodor Dostoevsky...");
		print(fyodorPhilosophicalBooks);
		System.out.println("\nXPath querry to list all the Colombian romance books...");
		print(colombianRomanceBooks);
		System.out.println("\nXPath querry to list all the English dystopian books...");
		print(englishDystopianBooks);
	}

	public static void solveWithDOM(File xmlInputFile) {
		DOMparser parser = new DOMparser(xmlInputFile);
		Interogation interogation = new Interogation(parser);

		print(interogation.getEnglishWriters());
		print(interogation.getRomanceBooks());
		print(interogation.getColombianRomanceBooks());
		print(interogation.getAllRomanceBooksOfWriter("Gabriel García Márquez"));
		print(interogation.getWritersWithDystopianGenre());
		print(interogation.getEnglishWritersAlive());
		print(interogation.getAllEnglishGenres());

		System.out.println("\n");
	}

	public static void loadLibrary() {
		System.out.println("Loading library...");
		try {
			File file = new File("library.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Library library = (Library) jaxbUnmarshaller.unmarshal(file);

			System.out.println(library);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		System.out.println("Validating XML schema based on XSD...");
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	public static void print(Set<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
	}

	public static void print(ArrayList<XMLElement> list) {
		for (XMLElement s : list) {
			System.out.println(s);
		}
	}
}
