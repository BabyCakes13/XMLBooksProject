package elementsParserXPath.operations.writerOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterParserByAlive extends WriterParser {
	private boolean alive;

	public WriterParserByAlive(boolean alive) {
		this.alive = alive;
	}

	public XMLElement elementOperation(Element el) {
		String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
		if ((writerDeathYear.equals("-") && (this.alive)) || (!writerDeathYear.equals("-") && (!this.alive)))
			return super.elementOperation(el);
		return null;
	}
}
