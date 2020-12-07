package elementsParserXPath;

import org.w3c.dom.Element;

import elements.Writer;
import elements.XMLElement;

public class BookParserByAuthor extends BookParser {
	public Writer writer;

	public BookParserByAuthor(Writer writer) {
		this.writer = writer;
	}

	public XMLElement parse(Element el) {
		String bookAuthor = el.getElementsByTagName("author").item(0).getTextContent();
		if (bookAuthor.equals(this.writer.getName()))
			return super.parse(el);
		return null;
	}
}
