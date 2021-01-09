package camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class CamelREST extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		restConfiguration().component("netty-http")
        .host("localhost")
        .port("9091")
        .bindingMode(RestBindingMode.auto);
		
		rest("/api101")
        .get("/hello")
            .description("Basic Hello World")
            .to("direct:rest1");
	}
}
