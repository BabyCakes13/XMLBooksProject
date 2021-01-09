package elements;

import java.util.ArrayList;

public class Books {

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
