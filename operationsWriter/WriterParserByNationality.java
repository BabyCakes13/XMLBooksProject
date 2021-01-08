package operationsWriter;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterParserByNationality extends WriterParser{
	public String nationality;

	public WriterParserByNationality(String nationality) {
		this.nationality = nationality;
	}

	public XMLElement elementOperation(Element el) {
		String writerNationality = el.getElementsByTagName("nationality").item(0).getTextContent();
		if (writerNationality.equals(this.nationality))
			return super.elementOperation(el);
		return null;
	}
}
