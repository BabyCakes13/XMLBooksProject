package camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:rest1")
        .routeId("Rest1Route")
        .log("START:")
        .setBody(constant("{hello: \"Hello, World!\"}"))
        .log("END:");
	}
	
}
