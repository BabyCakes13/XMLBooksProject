package elementsParserXPath;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elements.Genre;
import elements.Id;
import elements.XMLElement;
import elementsParserXPath.operations.genreOperations.GenreDeleterById;
import elementsParserXPath.operations.genreOperations.GenreDeleterByName;
import elementsParserXPath.operations.genreOperations.GenreEditorById;
import elementsParserXPath.operations.genreOperations.GenreEditorByName;
import elementsParserXPath.operations.genreOperations.GenreParserByName;

public class xPathParserGenre extends xPathParser {
	public xPathParserGenre(Document xmlDocument) {
		super(xmlDocument);
	}
	
	public ArrayList<XMLElement> parseGenres(String name) {
		return this.iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
	}
	
	public ArrayList<XMLElement> deleteGenres(String name) {
		this.iterateNodesAndApply("library/genres/genre", new GenreDeleterByName(name));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteGenres(Id id) {
		this.iterateNodesAndApply("library/genres/genre", new GenreDeleterById(id));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> editGenres(String name, String newName) {
		this.iterateNodesAndApply("library/genres/genre", new GenreEditorByName(name, newName));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> editGenres(Id id, Id newId) {
		this.iterateNodesAndApply("library/genres/genre", new GenreEditorById(id, newId));
		this.updateDocument();
		return null;
	}
	
	public void addGenre(Genre genre) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile("library/genres").evaluate(this.document,
					XPathConstants.NODESET);
			
			Node lastNode = nodeList.item(nodeList.getLength() - 1);
			Element element = document.createElement("genre");
			element.setAttribute("id", genre.getId());
			
			this.appendChild(element, "title", genre.getName());
			
			lastNode.appendChild(element);
			
			System.out.println("Added: " + genre.toString());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
}
