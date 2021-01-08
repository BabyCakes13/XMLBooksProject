package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class GenreDeleterByName extends GenreDeleter {
	public String name;

	public GenreDeleterByName(String name) {
		this.name = name;
	}

	public XMLElement elementOperation(Element el) {
		String genreName = el.getElementsByTagName("name").item(0).getTextContent();
		if (genreName.equals(this.name))
			return super.elementOperation(el);
		return null;
	}
}