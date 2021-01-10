package main;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class MainSetup {
	
	protected Document xmlDocument;
	
	public MainSetup(File file) {
		this.xmlDocument = this.setupDocument(new File("library.xml"));
	}
	
	public Document getXMLDocument() {
		return this.xmlDocument;
	}
	
	public Document setupDocument(File inputXMLFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputXMLFile);
			doc.getDocumentElement().normalize();

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
