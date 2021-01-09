package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elements.Author;
import elements.Book;
import elements.Genre;
import elements.Id;
import elements.Title;
import elements.Writer;
import elements.XMLElement;
import elementsParserXPath.operations.bookOperations.BookDeleterByAuthor;
import elementsParserXPath.operations.bookOperations.BookDeleterByGenre;
import elementsParserXPath.operations.bookOperations.BookDeleterById;
import elementsParserXPath.operations.bookOperations.BookDeleterByTitle;
import elementsParserXPath.operations.bookOperations.BookEditorByAuthor;
import elementsParserXPath.operations.bookOperations.BookEditorByGenre;
import elementsParserXPath.operations.bookOperations.BookEditorById;
import elementsParserXPath.operations.bookOperations.BookEditorByTitle;
import elementsParserXPath.operations.bookOperations.BookParserByAuthor;
import elementsParserXPath.operations.bookOperations.BookParserByGenre;
import elementsParserXPath.operations.genreOperations.GenreEditorById;
import elementsParserXPath.operations.genreOperations.GenreEditorByName;
import elementsParserXPath.operations.writerOperations.WriterParserByNationality;

public class xPathParserBook extends xPathParser {

	private String xPathBookArea = "library/books/book";

	public xPathParserBook(File inputXMLFile) {
		super(inputXMLFile);
	}

	public ArrayList<XMLElement> parseBooks(Genre genre) {
		return this.iterateNodesAndApply(this.xPathBookArea, new BookParserByGenre(genre));
	}

	public ArrayList<XMLElement> parseBooks(Genre genre, Writer writer) {
		ArrayList<XMLElement> booksByGenre = this.iterateNodesAndApply(this.xPathBookArea,
				new BookParserByGenre(genre));
		ArrayList<XMLElement> querryResult = new ArrayList<>();

		for (XMLElement element : booksByGenre) {
			Book book = (Book) element;
			if (book.getAuthor().equals(writer.getName())) {
				querryResult.add(book);
			}
		}

		return querryResult;
	}

	public ArrayList<XMLElement> parseBooks(Genre genre, String nationality) {
		ArrayList<XMLElement> booksByGenre = this.iterateNodesAndApply(this.xPathBookArea,
				new BookParserByGenre(genre));
		ArrayList<XMLElement> colombianAuthors = this.iterateNodesAndApply("library/writers/writer",
				new WriterParserByNationality(nationality));

		ArrayList<XMLElement> querryResult = new ArrayList<>();

		for (XMLElement bookElement : booksByGenre) {
			Book book = (Book) bookElement;

			for (XMLElement writerElement : colombianAuthors) {
				Writer writer = (Writer) writerElement;

				if (book.getAuthor().equals(writer.getName()) && (writer.getNationality().equals(nationality))) {
					querryResult.add(book);
				}
			}
		}

		return querryResult;
	}

	public ArrayList<XMLElement> parseBooks(Writer writer) {
		return this.iterateNodesAndApply(this.xPathBookArea, new BookParserByAuthor(writer));
	}

	public ArrayList<XMLElement> deleteBooks(Genre genre) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookDeleterByGenre(genre));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> deleteBooks(Title title) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookDeleterByTitle(title));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> deleteBooks(Id id) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookDeleterById(id));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> deleteBooks(Author author) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookDeleterByAuthor(author));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> editBooks(Title title, Title newTitle) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookEditorByTitle(title, newTitle));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> editBooks(Genre genre, Genre newGenre) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookEditorByGenre(genre, newGenre));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> editBooks(Author author, Author newAuthor) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookEditorByAuthor(author, newAuthor));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> editBooks(Id id, Id newId) {
		this.iterateNodesAndApply(this.xPathBookArea, new BookEditorById(id, newId));
		this.updateDocument();
		return null;
	}

	public void addBook(Book book) {
		try {
			NodeList nodeList = (NodeList) this.xPath.compile("library/books").evaluate(this.document,
					XPathConstants.NODESET);
			System.out.println(nodeList.getLength());
			
			Node lastNode = nodeList.item(nodeList.getLength() - 1);
			Element element = document.createElement("book");
			element.setAttribute("id", book.getId());
			
			this.appendChild(element, "title", book.getTitle());
			this.appendChild(element, "author", book.getAuthor());
			this.appendChild(element, "genre", book.getGenre());
			
			lastNode.appendChild(element);

			this.updateDocument();

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
