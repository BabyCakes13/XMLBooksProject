package elementsParserXPath;

import java.io.File;
import java.util.ArrayList;

import elements.XMLElement;
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
}
