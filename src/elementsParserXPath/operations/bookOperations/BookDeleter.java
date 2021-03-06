package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Book;
import elements.XMLElement;
import elementsParserXPath.ElementOperation;

public class BookDeleter implements ElementOperation{
	public XMLElement elementOperation(Element el) {
		String id = el.getAttribute("id");
		String title = el.getElementsByTagName("title").item(0).getTextContent();
		String author = el.getElementsByTagName("author").item(0).getTextContent();
		String genre = el.getElementsByTagName("genre").item(0).getTextContent();

		Book parsedBook = new Book(id, title, author, genre);
		
		el.getParentNode().removeChild(el);
		return parsedBook;
	}
}
