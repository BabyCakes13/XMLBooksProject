package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Author;
import elements.XMLElement;

public class WriterEditorByName extends WriterEditor {
	private Author name;
	private Author newName;

	public WriterEditorByName(Author name, Author newName) {
		this.name = name;
		this.newName = newName;
	}

	public XMLElement elementOperation(Element el) {
		String writerName = el.getElementsByTagName("name").item(0).getTextContent();
		if (writerName.equals(this.name.getName())) {
			el.getElementsByTagName("name").item(0).setTextContent(newName.getName());
			return super.elementOperation(el);
		}
			
		return null;
	}
}
