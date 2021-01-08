package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.XMLElement;
import elementsParserXPath.ElementOperation;

public class GenreDeleter implements ElementOperation{
	public XMLElement elementOperation(Element el) {
		el.getParentNode().removeChild(el);
		return null;
	}
}
