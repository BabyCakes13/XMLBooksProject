package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

import elements.Id;
import elements.Nationality;
import elements.WriterName;
import elements.XMLElement;
import elementsParserXPath.operations.writerOperations.WriterDeleterByAlive;
import elementsParserXPath.operations.writerOperations.WriterDeleterByDead;
import elementsParserXPath.operations.writerOperations.WriterDeleterById;
import elementsParserXPath.operations.writerOperations.WriterDeleterByName;
import elementsParserXPath.operations.writerOperations.WriterDeleterByNationality;
import elementsParserXPath.operations.writerOperations.WriterParserByAlive;
import elementsParserXPath.operations.writerOperations.WriterParserByName;
import elementsParserXPath.operations.writerOperations.WriterParserByNationality;

public class xPathParserWriter extends xPathParser {
	public xPathParserWriter(File inputXMLFile) {
		super(inputXMLFile);
	}
	
	public ArrayList<XMLElement> parseWriters(String element, String elementType) {
		if (elementType.equals("nationality")) {
			return this.iterateNodesAndApply("library/writers/writer", new WriterParserByNationality(element));
		} else if (elementType.equals("name")) {
			return this.iterateNodesAndApply("library/writers/writer", new WriterParserByName(element));
		}

		return null;
	}

	public ArrayList<XMLElement> parseWriters(boolean alive) {
		return this.iterateNodesAndApply("library/writers/writer", new WriterParserByAlive(alive));
	}
	
	public ArrayList<XMLElement> deleteWriters(String lifeStatus) {
		if(lifeStatus.equals("alive")) {
			this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByAlive());
		} else if (lifeStatus.equals("dead")) {
			this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByDead());
		}
		
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(WriterName name) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByName(name));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(Nationality nationality) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterByNationality(nationality));
		this.updateDocument();
		return null;
	}
	
	public ArrayList<XMLElement> deleteWriters(Id id) {
		this.iterateNodesAndApply("library/writers/writer", new WriterDeleterById(id));
		this.updateDocument();
		return null;
	}
}
