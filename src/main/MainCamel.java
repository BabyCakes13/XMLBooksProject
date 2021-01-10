package main;
import java.io.File;

import org.apache.camel.main.Main;
import org.w3c.dom.Document;

import camel.CamelREST;
import camel.CamelRoutes;

public class MainCamel extends MainSetup {
	
	public MainCamel(File file) {
		super(file);
	}
	public static void main(String args[]) throws Exception {
		Main main = new Main();
		MainCamel mainCamel = new MainCamel(new File("library.xml"));
		
		Document xmlDocument = mainCamel.getXMLDocument();
		
		main.configure().addRoutesBuilder(new CamelREST(xmlDocument));
		main.configure().addRoutesBuilder(new CamelRoutes(xmlDocument));
		
		main.run(args);
	}
}
