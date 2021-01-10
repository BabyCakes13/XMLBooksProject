package elementsParserXPath;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elements.Author;
import elements.Book;
import elements.Genre;
import elements.Id;
import elements.Nationality;
import elements.Title;
import elements.Writer;
import elements.XMLElement;
import elementsParserXPath.operations.genreOperations.GenreParser;
import elementsParserXPath.operations.writerOperations.WriterDeleterByAlive;
import elementsParserXPath.operations.writerOperations.WriterDeleterByDead;
import elementsParserXPath.operations.writerOperations.WriterDeleterById;
import elementsParserXPath.operations.writerOperations.WriterDeleterByName;
import elementsParserXPath.operations.writerOperations.WriterDeleterByNationality;
import elementsParserXPath.operations.writerOperations.WriterEditorById;
import elementsParserXPath.operations.writerOperations.WriterEditorByName;
import elementsParserXPath.operations.writerOperations.WriterEditorByNationality;
import elementsParserXPath.operations.writerOperations.WriterParser;
import elementsParserXPath.operations.writerOperations.WriterParserByAlive;
import elementsParserXPath.operations.writerOperations.WriterParserByName;
import elementsParserXPath.operations.writerOperations.WriterParserByNationality;

public class xPathParserWriter extends xPathParser {
	public xPathParserWriter(Document xmlDocument) {
		super(xmlDocument);
	}

	public ArrayList<XMLElement> parseAll() {
		System.out.println("\nDisplaying all writers from the library...");
		ElementOperation ep = new WriterParser();
		return this.iterateNodesAndApply("library/writers/writer", ep);
	}

	public ArrayList<XMLElement> parseWriters(Nationality nationality) {
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByNationality(nationality.getNationality()));
	}

	public ArrayList<XMLElement> parseWriters(Writer writer) {
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByName(writer.getName()));
	}

	public ArrayList<XMLElement> parseWriters(boolean alive) {
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByAlive(alive));
	}

	public String parseWritersCamel(String name, String nationality, String alive) {
		if (name != null) {
			return parseWritersFilterName(name);
		} else if (nationality != null) {
			return parseWritersFilterNationality(nationality);
		} else if (alive != null) {
			return parseWritersFilterAlive(alive);
		} else {
			return parseAllToString();
		}
	}
	public String parseWritersFilterNationality(String nationality) {
		System.out.println("Getting writer of nationality: " + nationality);
		return convert(this.parseWriters(new Nationality(nationality)));
	}

	public String parseWritersFilterName(String name) {
		System.out.println("Getting writer of name: " + name);
		return convert(this.parseWriters(new Writer(name)));
	}

	public String parseWritersFilterAlive(String alive) {
		if (alive.equals("yes")) {
			System.out.println("Getting alive writers.");
			return convert(parseWriters(true));
		} else if (alive.equals("no")) {
			System.out.println("Getting dead writers.");
			return convert(parseWriters(false));
		} else {
			return null;
		}
	}
	
	// SIMPLE DELETE

	public ArrayList<XMLElement> deleteWriters(String lifeStatus) {
		System.out.println("Deleting writer by life status: " + lifeStatus);
		ArrayList<XMLElement> deleted = new ArrayList<>();
		
		if(lifeStatus.equals("alive")) {
			deleted = this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByAlive());
		} else if (lifeStatus.equals("dead")) {
			deleted = this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByDead());
		} else {
			System.out.println("The Schrodinger's cat can be either alive, or dead.");
		}

		this.updateDocument();
		return deleted;
	}

	public ArrayList<XMLElement> deleteWriters(Author author) {
		System.out.println("Deleting writer by name: " + author.getName());
		ArrayList<XMLElement> deleted = this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByName(author));
		this.updateDocument();
		return deleted;
	}

	public ArrayList<XMLElement> deleteWriters(Nationality nationality) {
		System.out.println("Deleting writer by nationality: " + nationality.getNationality());
		ArrayList<XMLElement> deleted = this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByNationality(nationality));
		this.updateDocument();
		return deleted;
	}

	public ArrayList<XMLElement> deleteWriters(Id id) {
		System.out.println("Deleting writer by id: " + id.getId());
		ArrayList<XMLElement> deleted = this.iterateNodesAndApply("library/writers/writer", new WriterDeleterById(id));
		this.updateDocument();
		return deleted;
	}
	
	// CAMEL DELETE
	
	public String deleteCamelWriter(String id, String name, String nationality, String alive) {

		if (id != null) {
			return convert(deleteWriters(new Id(id)));
		}

		if (name != null) {
			return convert(deleteWriters(new Author(name)));
		}

		if (nationality != null) {
			return convert(deleteWriters(new Nationality(nationality)));
		}
		
		if (alive != null) {
			return convert(deleteWriters(alive));
		}

		return null;
	}
	
	// SIMPLE EDIT

	public ArrayList<XMLElement> editWriter(Id id, Id newId) {
		System.out.println("Replacing id: " + id + " with " + newId);
		ArrayList<XMLElement> edited = this.iterateNodesAndApply("library/writers/writer", new WriterEditorById(id, newId));
		this.updateDocument();
		return edited;
	}

	public ArrayList<XMLElement> editWriter(Author name, Author newName) {
		System.out.println("Replacing name: " + name.getName() + " with " + newName.getName());
		ArrayList<XMLElement> edited = this.iterateNodesAndApply("library/writers/writer", new WriterEditorByName(name, newName));
		this.updateDocument();
		return edited;
	}

	public ArrayList<XMLElement> editWriter(Nationality nationality, Nationality newNationality) {
		System.out.println("Replacing nationality: " + nationality.getNationality() + " with " + newNationality.getNationality());
		ArrayList<XMLElement> edited = this.iterateNodesAndApply("library/writers/writer", new WriterEditorByNationality(nationality, newNationality));
		this.updateDocument();
		return edited;
	}

	// CAMEL EDIT

	public String editCamelWriter(String name, String nationality,
								  String newName, String newNationality) {

		if (name != null && newName != null) {
			return convert(editWriter(new Author(name), new Author(newName)));
		}

		if (nationality != null && newNationality != null) {
			return convert(editWriter(new Nationality(nationality), new Nationality(newNationality)));
		}

		return null;
	}

	// SIMPLE ADD

	public String addWriter(Writer writer) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile("library/writers").evaluate(this.document,
					XPathConstants.NODESET);

			Node lastNode = nodeList.item(nodeList.getLength() - 1);
			Element element = document.createElement("writer");
			element.setAttribute("id", writer.getId());

			this.appendChild(element, "name", writer.getName());
			this.appendChild(element, "birthYear", writer.getBirthYear());
			this.appendChild(element, "deathYear", writer.getDeathYear());
			this.appendChild(element, "nationality", writer.getNationality());

			lastNode.appendChild(element);

			System.out.println("Added: " + writer.toString());

			this.updateDocument();
			return writer.toString();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	// CAMEL ADD

	public String addCamelWriter(String name, String nationality, String birthYear, String deathYear) {
		String randomId = UUID.randomUUID().toString();
		Writer writer= new Writer(randomId, name, birthYear, deathYear, nationality);

		return addWriter(writer);
	}
}
