package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

import elements.XMLElement;
import elementsParserXPath.operations.genreOperations.GenreParserByName;

public class xPathParserGenre extends xPathParser {
	public xPathParserGenre(File inputXMLFile) {
		super(inputXMLFile);
	}
	
	public ArrayList<XMLElement> parseGenres(String name) {
		return this.iterateNodesAndApply("library/genres/genre", new GenreParserByName(name));
	}
}
