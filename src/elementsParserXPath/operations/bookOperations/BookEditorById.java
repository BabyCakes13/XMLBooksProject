package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class BookEditorById extends BookEditor {
	private Id id;
	private Id newId;

	public BookEditorById(Id id, Id newId) {
		this.id = id;
		this.newId = newId;
	}

	public XMLElement elementOperation(Element el) {
		String bookId = el.getAttribute("id");
		if (bookId.equals(this.id.getId())) {
			el.setAttribute("id", newId.getId());
		}
			
		return null;
	}
}
