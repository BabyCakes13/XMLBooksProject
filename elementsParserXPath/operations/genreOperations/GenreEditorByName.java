package elementsParserXPath.operations.genreOperations;

import org.w3c.dom.Element;

import elements.XMLElement;

public class GenreEditorByName extends GenreEditor {
	private String name;
	private String newName;

	public GenreEditorByName(String name, String newName) {
		this.name = name;
		this.newName = newName;
	}

	public XMLElement elementOperation(Element el) {
		String genreName = el.getElementsByTagName("name").item(0).getTextContent();
		if (genreName.equals(this.name)) {
			el.getElementsByTagName("name").item(0).setTextContent(newName);
		}
			
		return null;
	}
}
