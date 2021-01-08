package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Genre;
import elements.XMLElement;

public class BookParserByGenre extends BookParser {
	public Genre genre;

	public BookParserByGenre(Genre genre) {
		this.genre = genre;
	}

	public XMLElement elementOperation(Element el) {
		String bookGenre = el.getElementsByTagName("genre").item(0).getTextContent();
		if (bookGenre.equals(this.genre.getName()))
			return super.elementOperation(el);
		return null;
	}
}
