package operationsBooks;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class BookDeleterById extends BookDeleter {
	public Id id;

	public BookDeleterById(Id id) {
		this.id = id;
	}

	public XMLElement elementOperation(Element el) {
		String bookId = el.getAttribute("id");
		if (bookId.equals(this.id.getId()))
			return super.elementOperation(el);
		return null;
	}
}
