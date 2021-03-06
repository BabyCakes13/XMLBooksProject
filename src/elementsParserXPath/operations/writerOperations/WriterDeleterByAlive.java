package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterDeleterByAlive extends WriterDeleter {

	public WriterDeleterByAlive() {}

	public XMLElement elementOperation(Element el) {
		String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
		if ((writerDeathYear.equals("-")))
			return super.elementOperation(el);
		return null;
	}
}