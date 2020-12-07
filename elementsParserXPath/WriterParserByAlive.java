package elementsParserXPath;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterParserByAlive extends WriterParser {
	private boolean alive;

	public WriterParserByAlive(boolean alive) {
		this.alive = alive;
	}

	public XMLElement parse(Element el) {
		String writerDeathYear = el.getElementsByTagName("deathYear").item(0).getTextContent();
		if ((writerDeathYear.equals("-") && (this.alive)) || (!writerDeathYear.equals("-") && (!this.alive)))
			return super.parse(el);
		return null;
	}
}
