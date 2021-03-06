package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.Genre;
import elements.XMLElement;
import elementsParserXPath.ElementOperation;

public class GenreDeleter implements ElementOperation{
	public XMLElement elementOperation(Element el) {
		String id = el.getAttribute("id");
		String name = el.getElementsByTagName("name").item(0).getTextContent();

		Genre parsedGenre = new Genre(id, name);
		el.getParentNode().removeChild(el);
		return parsedGenre;
	}
}
