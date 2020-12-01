import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Set;

import java.io.IOException;

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

    loadLibrary();

    DOMparser parser = new DOMparser(new File("library.xml"));
    Interogation interogation = new Interogation(parser);

    System.out.println("\nQuerry for all English writers:");
    print(interogation.getEnglishWriters());
    System.out.println("\nQuerry for all romance genre books:");
    print(interogation.getRomanceBooks());
    System.out.println("\nQuerry for all Colombian romance books.");
    print(interogation.getColombianRomanceBooks());
    System.out.println("\nQuerry for all writers with dystopian books.");
    print(interogation.getWritersWithDystopianGenre());
    System.out.println("\nQuerry for all English writers still alive:");
    print(interogation.getEnglishWritersAlive());
    System.out.println("\nQuerry for all English genres:");
    print(interogation.getAllEnglishGenres());
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
