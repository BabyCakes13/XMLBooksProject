package elementsParserXPath;

import org.w3c.dom.Element;

import elements.XMLElement;

public class WriterDeleter implements ElementOperation{
	public XMLElement elementOperation(Element el) {
		el.getParentNode().removeChild(el);
		return null;
	}
}
