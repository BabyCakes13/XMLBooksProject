package camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.w3c.dom.Document;

import elementsParserXPath.xPathParserBook;
import elementsParserXPath.xPathParserGenre;
import elementsParserXPath.xPathParserWriter;

public class CamelREST extends RouteBuilder{

	private xPathParserBook xmlBookInteractor;
	private xPathParserWriter xmlWriterInteractor;
	private xPathParserGenre xmlGenreInteractor;

	public CamelREST(Document xmlDocument) {
		this.xmlBookInteractor = new xPathParserBook(xmlDocument);
		this.xmlWriterInteractor = new xPathParserWriter(xmlDocument);
		this.xmlGenreInteractor = new xPathParserGenre(xmlDocument);
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
			.get("/books/list?genre={genre}&writer={writer}&nationality={nationality}")
	        .produces("text/plain")
	        .route()
	        .bean(xmlBookInteractor, "parseBooksCamel(${header.genre},${header.writer},${header.nationality})")
	        .endRest();
		
		rest("/library")
			.get("/writers/list?name={name}&nationality={nationality}&alive={alive}")
	        .produces("text/plain")
	        .route()
	        .bean(xmlWriterInteractor, "parseWritersCamel(${header.name},${header.nationality},${header.alive})")
	        .endRest();
		
		rest("/library")
			.get("/genres/list?name={name}")
	        .produces("text/plain")
	        .route()
	        .bean(xmlGenreInteractor, "parseGenresCamel(${header.name})")
	        .endRest();
		
		rest("/library")
			.get("/books/add?title={title}&writer={writer}&genre={genre}")
	        .produces("text/plain")
	        .route()
	        .bean(xmlBookInteractor, "addCamelBook(${header.title},${header.writer},${header.genre})")
	        .endRest();
		
		rest("/library")
		.get("/genres/add?name={name}")
        .produces("text/plain")
        .route()
        .bean(xmlGenreInteractor, "addCamelGenre(${header.name})")
        .endRest();
		
		rest("/library")
			.get("/writers/add?name={name}&nationality={nationality}&birthYear={birthYear}&deathYear={deathYear}")
	        .produces("text/plain")
	        .route()
	        .bean(xmlWriterInteractor, "addCamelWriter(${header.name},${header.nationality},${header.birthYear},${header.deathYear})")
	        .endRest();
	}
}
