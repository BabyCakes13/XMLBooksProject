package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Title;
import elements.XMLElement;

public class BookDeleterByTitle extends BookDeleter {
	public Title title;

	public BookDeleterByTitle(Title title) {
		this.title = title;
	}

	public XMLElement elementOperation(Element el) {
		String bookTitle = el.getElementsByTagName("title").item(0).getTextContent();
		if (bookTitle.equals(this.title.getTitle()))
			return super.elementOperation(el);
		return null;
	}
}
