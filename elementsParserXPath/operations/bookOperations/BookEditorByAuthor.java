package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Author;
import elements.XMLElement;

public class BookEditorByAuthor extends BookEditor {
	private Author author;
	private Author newAuthor;

	public BookEditorByAuthor(Author author,Author newAuthor) {
		this.author = author;
		this.newAuthor = newAuthor;
	}

	public XMLElement elementOperation(Element el) {
		String bookAuthor = el.getElementsByTagName("author").item(0).getTextContent();
		if (bookAuthor.equals(this.author.getName())) {
			el.getElementsByTagName("author").item(0).setTextContent(newAuthor.getName());
		}
		return null;
	}
}
