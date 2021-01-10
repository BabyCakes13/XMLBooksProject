package camel;

import org.apache.camel.builder.RouteBuilder;
import org.w3c.dom.Document;

import elementsParserXPath.xPathParserBook;

public class CamelRoutes extends RouteBuilder {

	private xPathParserBook xmlBookInteractor;

	public CamelRoutes(Document xmlDocument) {
		this.xmlBookInteractor = new xPathParserBook(xmlDocument);
	}

	@Override
	public void configure() throws Exception {
		from("direct:library-hello")
		.routeId("LibraryHello")
		.log("START:")
		.setBody(constant("{hello: \"Hello, World!\"}"))
		.log("END:");
	}

}
