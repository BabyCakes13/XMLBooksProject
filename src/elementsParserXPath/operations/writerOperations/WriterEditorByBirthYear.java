package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterEditorByBirthYear extends WriterEditor {
	private String birthYear;
	private String newBirthYear;

	public WriterEditorByBirthYear(String birthYear, String newBirthYear) {
		this.birthYear = birthYear;
		this.newBirthYear = newBirthYear;
	}

	public XMLElement elementOperation(Element el) {
		String writerBirthYear = el.getElementsByTagName("birthYear").item(0).getTextContent();
		if (writerBirthYear.equals(this.birthYear)) {
			el.getElementsByTagName("birthYear").item(0).setTextContent(newBirthYear);
		}
			
		return null;
	}
}
