package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class GenreDeleterById extends GenreDeleter {
	public Id id;

	public GenreDeleterById(Id id) {
		this.id = id;
	}

	public XMLElement elementOperation(Element el) {
		String genreId = el.getAttribute("id");
		if (genreId.equals(this.id.getId()))
			return super.elementOperation(el);
		return null;
	}
}