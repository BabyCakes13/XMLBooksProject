package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Author;
import elements.XMLElement;

public class BookDeleterByAuthor extends BookDeleter {
	public Author author;

	public BookDeleterByAuthor(Author author) {
		this.author = author;
	}

	public XMLElement elementOperation(Element el) {
		String bookAuthor = el.getElementsByTagName("author").item(0).getTextContent();
		if (bookAuthor.equals(this.author.getName()))
			return super.elementOperation(el);
		return null;
	}
}
