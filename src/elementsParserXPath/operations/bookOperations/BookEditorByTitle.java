package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Title;
import elements.XMLElement;

public class BookEditorByTitle extends BookEditor {
	private Title title;
	private Title newTitle;

	public BookEditorByTitle(Title title, Title newTitle) {
		this.title = title;
		this.newTitle = newTitle;
	}

	public XMLElement elementOperation(Element el) {
		String bookTitle = el.getElementsByTagName("title").item(0).getTextContent();
		if (bookTitle.equals(this.title.getTitle())) {
			el.getElementsByTagName("title").item(0).setTextContent(newTitle.getTitle());
			return super.elementOperation(el);
		}
		return null;
	}
}
