package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class GenreEditorById extends GenreEditor {
	private Id id;
	private Id newId;

	public GenreEditorById(Id id, Id newId) {
		this.id = id;
		this.newId = newId;
	}

	public XMLElement elementOperation(Element el) {
		String genreId = el.getAttribute("id");
		if (genreId.equals(this.id.getId())) {
			el.setAttribute(id.getId(), newId.getId());
		}
			
		return null;
	}
}
