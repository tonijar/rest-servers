package jar.toni.sample.restexpress.rest.server;

import org.restexpress.RestExpress;
import org.restexpress.serialization.DefaultSerializationProvider;
import org.restexpress.util.Environment;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		RestExpress.setDefaultSerializationProvider(new DefaultSerializationProvider());
		Configuration config = Environment.load(args, Configuration.class);
		RestExpress server = new RestExpress().setName(config.getName()).setPort(config.getPort());

		defineRoutes(server, config);

		if (config.getWorkerCount() > 0) {
			server.setIoThreadCount(config.getWorkerCount());
		}

		if (config.getExecutorThreadCount() > 0) {
			server.setExecutorThreadCount(config.getExecutorThreadCount());
		}

		mapExceptions(server);
		server.bind();
		server.awaitShutdown();
	}

	/**
	 * @param server
	 * @param config
	 */
	private static void defineRoutes(RestExpress server, Configuration config) {
		// This route supports GET, POST, PUT, DELETE echoing the 'name' query-string
		// parameter in the response.
		server.uri("/hello/{name}", config.getHelloWorldController()).performSerialization();
	}

	/**
	 * @param server
	 */
	private static void mapExceptions(RestExpress server) {
		// server
		// .mapException(ItemNotFoundException.class, NotFoundException.class)
		// .mapException(DuplicateItemException.class, ConflictException.class)
		// .mapException(ValidationException.class, BadRequestException.class);
	}
}
