package elementsParserXPath;

import org.w3c.dom.Element;

import elements.XMLElement;

public class GenreParserByName extends GenreParser {
	public String name;

	public GenreParserByName(String name) {
		this.name = name;
	}

	public XMLElement parse(Element el) {
		String genreName = el.getElementsByTagName("name").item(0).getTextContent();
		if (genreName.equals(this.name))
			return super.parse(el);
		return null;
	}
}
