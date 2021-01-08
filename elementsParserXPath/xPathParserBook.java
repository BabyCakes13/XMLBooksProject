package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

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
import elementsParserXPath.operations.bookOperations.BookParserByAuthor;
import elementsParserXPath.operations.bookOperations.BookParserByGenre;
import elementsParserXPath.operations.writerOperations.WriterParserByNationality;

public class xPathParserBook extends xPathParser{
	
	public xPathParserBook(File inputXMLFile) {
		super(inputXMLFile);
	}
	public ArrayList<XMLElement> parseBooks(Genre genre) {
		return this.iterateNodesAndApply("library/books/book", new BookParserByGenre(genre));
	}

	public ArrayList<XMLElement> deleteBooks(Genre genre) {
		this.iterateNodesAndApply("library/books/book", new BookDeleterByGenre(genre));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteBooks(Title title) {
		this.iterateNodesAndApply("library/books/book", new BookDeleterByTitle(title));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteBooks(Id id) {
		this.iterateNodesAndApply("library/books/book", new BookDeleterById(id));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteBooks(Author author) {
		this.iterateNodesAndApply("library/books/book", new BookDeleterByAuthor(author));
		this.updateDocument();
		return null;
	}

	public ArrayList<XMLElement> parseBooks(Genre genre, Writer writer) {
		ArrayList<XMLElement> booksByGenre = this.iterateNodesAndApply("library/books/book",
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
		ArrayList<XMLElement> booksByGenre = this.iterateNodesAndApply("library/books/book",
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
		return this.iterateNodesAndApply("library/books/book", new BookParserByAuthor(writer));
	}
}
