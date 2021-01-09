import org.apache.camel.main.Main;
import org.apache.camel.main.RestConfigurationProperties;

import camel.CamelREST;
import camel.CamelRoutes;

public class MainCamel {
	public static void main(String args[]) throws Exception {
		Main main = new Main();

		main.configure().addRoutesBuilder(new CamelREST());
		main.configure().addRoutesBuilder(new CamelRoutes());
		main.run(args);
	}
}
