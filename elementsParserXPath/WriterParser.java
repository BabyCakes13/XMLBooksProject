package elementsParserXPath;

import org.w3c.dom.Element;

import elements.Writer;
import elements.XMLElement;

public class WriterParser implements ElementOperation {
	public XMLElement elementOperation(Element el) {
		String id = el.getAttribute("id");
		String name = el.getElementsByTagName("name").item(0).getTextContent();
		String birthYear = el.getElementsByTagName("birthYear").item(0).getTextContent();
		String deathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
		String nationality = el.getElementsByTagName("nationality").item(0).getTextContent();

		Writer parsedWriter = new Writer(id, name, birthYear, deathYear, nationality);
		
		return parsedWriter;
	}
}
