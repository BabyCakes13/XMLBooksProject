package camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.w3c.dom.Document;

import elementsParserXPath.xPathParserBook;

public class CamelREST extends RouteBuilder{

	private xPathParserBook xmlBookInteractor;

	public CamelREST(Document xmlDocument) {
		this.xmlBookInteractor = new xPathParserBook(xmlDocument);
	}

	@Override
	public void configure() throws Exception {
		restConfiguration().component("netty-http")
		.host("localhost")
		.port("9091")
		.bindingMode(RestBindingMode.auto);

		rest("/library")
		.get("/hello")
		.description("Basic Hello World")
		.to("direct:library-hello");

		rest("/library")
		.get("/books")
		.produces("application/xml")
		.route()
		.bean(xmlBookInteractor, "parseAllToBytes")
		.endRest();
	}
}
