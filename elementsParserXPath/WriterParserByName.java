package elementsParserXPath;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterParserByName extends WriterParser {
	public String name;

	public WriterParserByName(String name) {
		this.name = name;
	}

	public XMLElement parse(Element el) {
		String writerName = el.getElementsByTagName("name").item(0).getTextContent();
		if (writerName.equals(this.name))
			return super.parse(el);
		return null;
	}
}
