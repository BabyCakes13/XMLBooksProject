package elementsParserXPath;

import org.w3c.dom.Element;

import elements.Genre;
import elements.XMLElement;

public class BookParserByGenre extends BookParser {
	public Genre genre;

	public BookParserByGenre(Genre genre) {
		this.genre = genre;
	}

	public XMLElement parse(Element el) {
		String bookGenre = el.getElementsByTagName("genre").item(0).getTextContent();
		if (bookGenre.equals(this.genre.getName()))
			return super.parse(el);
		return null;
	}
}
