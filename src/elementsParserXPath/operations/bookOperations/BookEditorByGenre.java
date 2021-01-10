package elementsParserXPath.operations.bookOperations;

import org.w3c.dom.Element;

import elements.Genre;
import elements.XMLElement;

public class BookEditorByGenre extends BookEditor {
	private Genre genre;
	private Genre newGenre;

	public BookEditorByGenre(Genre genre,Genre newGenre) {
		this.genre = genre;
		this.newGenre = newGenre;
	}

	public XMLElement elementOperation(Element el) {
		String bookGenre = el.getElementsByTagName("genre").item(0).getTextContent();
		if (bookGenre.equals(this.genre.getName())) {
			el.getElementsByTagName("genre").item(0).setTextContent(newGenre.getName());
			return super.elementOperation(el);
		}
		return null;
	}
}
