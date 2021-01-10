package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterEditorByDeathYear extends WriterEditor {
	private String deathYear;
	private String newDeathYear;

	public WriterEditorByDeathYear(String deathYear, String newDeathYear) {
		this.deathYear = deathYear;
		this.newDeathYear = newDeathYear;
	}

	public XMLElement elementOperation(Element el) {
		String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
		if (writerDeathYear.equals(this.deathYear)) {
			el.getElementsByTagName("deathYear").item(0).setTextContent(newDeathYear);
			return super.elementOperation(el);
		}
			
		return null;
	}
}
