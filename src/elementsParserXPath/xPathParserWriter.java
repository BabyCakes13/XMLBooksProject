package elementsParserXPath;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elements.Author;
import elements.Genre;
import elements.Id;
import elements.Nationality;
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
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByNationality(writer.getName()));
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
	
	public ArrayList<XMLElement> deleteWriters(String lifeStatus) {
		if(lifeStatus.equals("alive")) {
			this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByAlive());
		} else if (lifeStatus.equals("dead")) {
			this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByDead());
		}
		
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(Author author) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByName(author));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(Nationality nationality) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByNationality(nationality));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(Id id) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterById(id));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> editWriter(Id id, Id newId) {
		this.iterateNodesAndApply("library/writers/writer", new WriterEditorById(id, newId));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> editWriter(Author name, Author newName) {
		this.iterateNodesAndApply("library/writers/writer", new WriterEditorByName(name, newName));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> editWriter(Nationality nationality, Nationality newNationality) {
		this.iterateNodesAndApply("library/writers/writer", new WriterEditorByNationality(nationality, newNationality));
		this.updateDocument();
		return null;
	}
	
	public void addWriter(Writer writer) {
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
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
