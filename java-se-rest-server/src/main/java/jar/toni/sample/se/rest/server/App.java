package jar.toni.sample.se.rest.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 *
 */
@SuppressWarnings("restriction")
public class App {
	private static final int PORT = 9090;

	private static final String HEADER_ALLOW = "Allow";
	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static final Charset CHARSET = StandardCharsets.UTF_8;

	private static final int STATUS_OK = 200;
	private static final int STATUS_METHOD_NOT_ALLOWED = 405;

	private static final int NO_RESPONSE_LENGTH = -1;

	private static final String METHOD_GET = "GET";
	private static final String METHOD_OPTIONS = "OPTIONS";
	private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;

	public static void main(final String... args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
		server.createContext("/hello", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			final Headers headers = t.getResponseHeaders();
			final String requestMethod = t.getRequestMethod().toUpperCase();
			switch (requestMethod) {
			case METHOD_GET:
				String path = t.getRequestURI().getPath();
				String name = path.substring(path.lastIndexOf("/") + 1);
				final String responseBody = new Gson().toJson(new Hello("Hello " + name + "!"));
				headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
				final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
				t.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
				t.getResponseBody().write(rawResponseBody);
				break;
			case METHOD_OPTIONS:
				headers.set(HEADER_ALLOW, ALLOWED_METHODS);
				t.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
				break;
			default:
				headers.set(HEADER_ALLOW, ALLOWED_METHODS);
				t.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
				break;
			}
		}
	}

	static class Hello {
		private String content;

		public Hello(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
