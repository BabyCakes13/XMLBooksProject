package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Id;
import elements.XMLElement;

public class WriterDeleterById extends WriterDeleter {
	public Id id;

	public WriterDeleterById(Id id) {
		this.id = id;
	}

	public XMLElement elementOperation(Element el) {
		String writerId = el.getAttribute("id");
		if (writerId.equals(this.id.getId()))
			return super.elementOperation(el);
		return null;
	}
}