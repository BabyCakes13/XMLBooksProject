package elementsParserXPath;

import org.w3c.dom.Element;

import elements.Genre;
import elements.XMLElement;

public class GenreParser implements ElementOperation {
	public XMLElement elementOperation(Element el) {
		String id = el.getAttribute("id");
		String name = el.getElementsByTagName("name").item(0).getTextContent();

		Genre parsedGenre = new Genre(id, name);

		return parsedGenre;
	}
}
