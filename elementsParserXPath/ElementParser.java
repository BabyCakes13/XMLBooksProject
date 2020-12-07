package elementsParserXPath;

import org.w3c.dom.Element;
import elements.XMLElement;

public interface ElementParser {
	public XMLElement parse(Element el);
}
