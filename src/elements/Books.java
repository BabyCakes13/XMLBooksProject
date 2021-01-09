package elements;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Books {

	@XmlElement(name = "book")
	private ArrayList<Book> books;

	public Books() {
	}

	public Books(ArrayList<Book> books) {
		super();
		this.books = books;
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	@Override
	public String toString() {
		String toString = "";
		
		for (Book book : this.books) {
			toString = toString + book + "\n";
		}
		
		return toString;
	}
}
