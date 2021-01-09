package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Author;
import elements.XMLElement;

public class WriterDeleterByName extends WriterDeleter {
	public Author author;

	public WriterDeleterByName(Author author) {
		this.author = author;
	}

	public XMLElement elementOperation(Element el) {
		String writerName = el.getElementsByTagName("name").item(0).getTextContent();
		if (writerName.equals(this.author.getName()))
			return super.elementOperation(el);
		return null;
	}
}