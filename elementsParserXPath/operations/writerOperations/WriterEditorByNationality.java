package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.Nationality;
import elements.XMLElement;

public class WriterEditorByNationality extends WriterEditor {
	private Nationality nationality;
	private Nationality newNationality;

	public WriterEditorByNationality(Nationality nationality, Nationality newNationality) {
		this.nationality = nationality;
		this.newNationality = newNationality;
	}

	public XMLElement elementOperation(Element el) {
		String writerNationality = el.getElementsByTagName("nationality").item(0).getTextContent();
		if (writerNationality.equals(this.nationality.getNationality())) {
			el.getElementsByTagName("nationality").item(0).setTextContent(newNationality.getNationality());
		}
			
		return null;
	}
}
