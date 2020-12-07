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

import elements.Genre;
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
		solveWithDOM(xmlInputFile);
		solveWithXPath(xmlInputFile);
	}

	public static void solveWithXPath(File xmlInputFile) {
		xPathParser xPath = new xPathParser(xmlInputFile);
		
		simpleXPathQuerries(xPath);
		mediumXPathQuerries(xPath);
	}
	
	public static void simpleXPathQuerries(xPathParser xPath) {
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
	
	public static void mediumXPathQuerries(xPathParser xPath) {
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
		System.out.println("\nXPath querry to list all the English writers...");
		print(englishWriters);
		System.out.println("\nXPath querry to list all alive writers...");
		print(aliveWriters);
		System.out.println("\nXPath querry to list all dead writers...");
		print(deadAuthors);
		System.out.println("\nXPath querry to list information about the romance genre...");
		print(romanceGenre);
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
