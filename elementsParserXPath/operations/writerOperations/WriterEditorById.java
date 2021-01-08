package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class WriterEditorById extends WriterEditor {
	private Id id;
	private Id newId;

	public WriterEditorById(Id id, Id newId) {
		this.id = id;
		this.newId = newId;
	}

	public XMLElement elementOperation(Element el) {
		String writerId = el.getAttribute("id");
		if (writerId.equals(this.id.getId())) {
			el.setAttribute("id", newId.getId());
		}
			
		return null;
	}
}
