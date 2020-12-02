import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Set;

import java.io.IOException;
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class Main {
  public static void main(String[] args) {
    System.out.println("Starting the application.\n");

    if (!validateXMLSchema("library.xsd", "library.xml")) {
    	System.out.println("The XML schema is not valid. Aborting mission.");
    } else {
    	System.out.println("The XML schema is valid.");
    }

    File xmlInputFile = new File("library.xml");
    loadLibrary();
    solveWithDOM(xmlInputFile);
    solveWithXPath(xmlInputFile);
  }

  public static void solveWithXPath(File xmlInputFile) {
    xPathParser xPath = new xPathParser(xmlInputFile);
    xPath.tryXPath();
  }

  public static void solveWithDOM(File xmlInputFile) {
    DOMparser parser = new DOMparser(xmlInputFile);
    Interogation interogation = new Interogation(parser);

    print(interogation.getEnglishWriters());
    print(interogation.getRomanceBooks());
    print(interogation.getColombianRomanceBooks());
    print(interogation.getAllRomanceBooksOfWriter("Gabriel García Márquez"));
    print(interogation.getWritersWithDystopianGenre());
    print(interogation.getEnglishWritersAlive());
    print(interogation.getAllEnglishGenres());

    System.out.println("\n");
  }

  public static void loadLibrary() {
    System.out.println("Loading library...");
    try {
       File file = new File("library.xml");
       JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);

       Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       Library library = (Library) jaxbUnmarshaller.unmarshal(file);

       System.out.println(library);
       } catch (JAXBException e) {
        e.printStackTrace();
      }
  }

  public static boolean validateXMLSchema(String xsdPath, String xmlPath){
      System.out.println("Validating XML schema based on XSD...");
      try {
          SchemaFactory factory =
                  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
          Schema schema = factory.newSchema(new File(xsdPath));
          Validator validator = schema.newValidator();
          validator.validate(new StreamSource(new File(xmlPath)));
      } catch (IOException | SAXException e) {
          System.out.println("Exception: "+e.getMessage());
          return false;
      }
      return true;
  }

  public static void print(Set<String> list) {
    for(String s: list) {
      System.out.println(s);
    }
  }
}
