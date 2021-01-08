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
import elements.Genre;
import elements.Id;
import elements.Title;
import elements.Writer;
import elements.XMLElement;
import elementsParserXPath.xPathParser;

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
		// solveWithDOM(xmlInputFile);
		solveWithXPath(xmlInputFile);
	}

	public static void solveWithXPath(File xmlInputFile) {
		xPathParser xPath = new xPathParser(xmlInputFile);
		
		//oneFilterDelete(xPath);
		twoFilterDelete(xPath);
		// simpleXPathQuerries(xPath);
		// mediumXPathQuerries(xPath);
		// complexXPathQuerries(xPath);
	}
	
	/**
	 * Method which deletes all entries with the filter book, writer or genre.
	 * @param xPath xPath object interogator.
	 */
	public static void oneFilterDelete(xPathParser xPath) {
		xPath.deleteAll("library/books/book");
		xPath.deleteAll("library/writers/writer");
		xPath.deleteAll("library/genres/genre");
	}
	
	public static void twoFilterDelete(xPathParser xPath) {
		xPath.deleteBooks(new Genre("Romance"));
		xPath.deleteBooks(new Title("Brave New World"));
		xPath.deleteBooks(new Id("b4"));
		xPath.deleteBooks(new Author("George Orwell"));
		
		
	}

	public static void oneFilterXPathQuerries(xPathParser xPath) {
		ArrayList<XMLElement> allBooks = xPath.parseAll("library/books/book");
		ArrayList<XMLElement> allWriters = xPath.parseAll("library/writers/writer");
		ArrayList<XMLElement> allGenres = xPath.parseAll("library/genres/genre");

		System.out.println("\nXPath querry to list all the books from the library...");
		print(allBooks);
		System.out.println("\nXPath querry to list all the writers from the library...");
		print(allWriters);
		System.out.println("\nXPath querry to list all the genres from the library...");
		print(allGenres);
	}

	public static void twoFilterXPathQuerries(xPathParser xPath) {
		ArrayList<XMLElement> romanceBooks = xPath.parseBooks(new Genre("Romance"));
		ArrayList<XMLElement> fyodorWriter = xPath.parseBooks(new Writer("Fyodor Dostoevsky"));

		ArrayList<XMLElement> fyodorBooks = xPath.parseWriters("Fyodor Dostoevsky", "name");
		ArrayList<XMLElement> englishWriters = xPath.parseWriters("English", "nationality");
		ArrayList<XMLElement> aliveWriters = xPath.parseWriters(true);
		ArrayList<XMLElement> deadAuthors = xPath.parseWriters(false);

		ArrayList<XMLElement> romanceGenre = xPath.parseGenres("Romance");

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

	public static void threeFilterXPathQuerries(xPathParser xPath) {
		ArrayList<XMLElement> gabrielRealism = xPath.parseBooks(new Genre("Realism"), new Writer("Gabriel García Márquez"));
		ArrayList<XMLElement> fyodorPhilosophicalBooks = xPath.parseBooks(new Genre("Philosophical"), new Writer("Fyodor Dostoevsky"));

		ArrayList<XMLElement> colombianRomanceBooks = xPath.parseBooks(new Genre("Romance"), "Colombian");
		ArrayList<XMLElement> englishDystopianBooks = xPath.parseBooks(new Genre("Dystopian"), "English");

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
