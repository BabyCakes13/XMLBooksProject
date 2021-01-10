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
import elements.Genre;
import elements.Id;
import elements.Nationality;
import elements.Title;
import elements.Writer;
import elements.XMLElement;
import elementsParserXPath.operations.genreOperations.GenreDeleterById;
import elementsParserXPath.operations.genreOperations.GenreDeleterByName;
import elementsParserXPath.operations.genreOperations.GenreEditorById;
import elementsParserXPath.operations.genreOperations.GenreEditorByName;
import elementsParserXPath.operations.genreOperations.GenreParser;
import elementsParserXPath.operations.genreOperations.GenreParserByName;

public class xPathParserGenre extends xPathParser {
	public xPathParserGenre(Document xmlDocument) {
		super(xmlDocument);
	}

	public String parseGenresCamel(String name) {
		if (name != null) {
			return parseGenresFilterName(name);
		} else {
			return parseAllToString();
		}
	}

	public ArrayList<XMLElement> parseAll() {
		System.out.println("\nDisplaying all genres from the library...");
		ElementOperation ep = new GenreParser();
		return this.iterateNodesAndApply("library/genres/genre", ep);
	}

	public ArrayList<XMLElement> parseGenres(String name) {
		return this.iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
	}

	private String parseGenresFilterName(String name) {
		System.out.println("Getting genre of name: " + name);
		return convert(parseGenres(name));
	}
	
	// SIMPLE DELETE

	public ArrayList<XMLElement> deleteGenres(String name) {
		System.out.println("Deleting genre by name: " + name);
		ArrayList<XMLElement> deleted = this.iterateNodesAndApply("library/genres/genre", new GenreDeleterByName(name));
		this.updateDocument();
		return deleted;
	}

	public ArrayList<XMLElement> deleteGenres(Id id) {
		System.out.println("Deleting genre by id: " + id.getId());
		ArrayList<XMLElement> deleted = this.iterateNodesAndApply("library/genres/genre", new GenreDeleterById(id));
		this.updateDocument();
		return deleted;
	}
	
	// CAMEL DELETE
	
	public String deleteCamelGenre(String id, String name) {

		if (name != null) {
			return convert(deleteGenres(name));
		}
		
		if (id != null) {
			return convert(deleteGenres(new Id(id)));
		}

		return null;
	}

	// SIMPLE EDIT

	public ArrayList<XMLElement> editGenres(String name, String newName) {
		System.out.println("Replacing name: " + name+ " with " + newName);
		ArrayList<XMLElement> edited = this.iterateNodesAndApply("library/genres/genre", new GenreEditorByName(name, newName));
		this.updateDocument();
		return edited;
	}

	public ArrayList<XMLElement> editGenres(Id id, Id newId) {
		this.iterateNodesAndApply("library/genres/genre", new GenreEditorById(id, newId));
		this.updateDocument();
		return null;
	}

	// CAMEL EDIT

	public String editCamelGenre(String name, String newName) {

		if (name != null && newName != null) {
			return convert(editGenres(name, newName));
		}

		return null;
	}

	// SIMPLE ADD

	public String addGenre(Genre genre) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile("library/genres").evaluate(this.document,
					XPathConstants.NODESET);

			Node lastNode = nodeList.item(nodeList.getLength() - 1);
			Element element = document.createElement("genre");
			element.setAttribute("id", genre.getId());

			this.appendChild(element, "title", genre.getName());

			lastNode.appendChild(element);

			System.out.println("Added: " + genre.toString());

			this.updateDocument();
			return genre.toString();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	// CAMEL ADD

	public String addCamelGenre(String name) {
		String randomId = UUID.randomUUID().toString();
		Genre genre= new Genre(randomId, name);

		return addGenre(genre);
	}

}
