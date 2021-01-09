package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Nationality;
import elements.XMLElement;

public class WriterDeleterByNationality extends WriterDeleter {
	public Nationality nationality;

	public WriterDeleterByNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public XMLElement elementOperation(Element el) {
		String writerNationality = el.getElementsByTagName("nationality").item(0).getTextContent();
		if (writerNationality.equals(this.nationality.getNationality()))
			return super.elementOperation(el);
		return null;
	}
}