package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

import elements.Id;
import elements.XMLElement;
import elementsParserXPath.operations.genreOperations.GenreDeleterById;
import elementsParserXPath.operations.genreOperations.GenreDeleterByName;
import elementsParserXPath.operations.genreOperations.GenreEditorById;
import elementsParserXPath.operations.genreOperations.GenreEditorByName;
import elementsParserXPath.operations.genreOperations.GenreParserByName;

public class xPathParserGenre extends xPathParser {
	public xPathParserGenre(File inputXMLFile) {
		super(inputXMLFile);
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
	
}