package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.WriterName;
import elements.XMLElement;

public class WriterDeleterByName extends WriterDeleter {
	public WriterName name;

	public WriterDeleterByName(WriterName name) {
		this.name = name;
	}

	public XMLElement elementOperation(Element el) {
		String writerName = el.getElementsByTagName("name").item(0).getTextContent();
		if (writerName.equals(this.name.getName()))
			return super.elementOperation(el);
		return null;
	}
}