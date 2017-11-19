package jar.toni.sample.spark.rest.server;

import static spark.Spark.get;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		// matches "GET /hello/foo" and "GET /hello/bar"
		// request.params(":name") is 'foo' or 'bar'
		get("/hello/:name", (request, response) -> {
			response.type("application/json");
			return new Gson().toJson(new Hello("Hello " + request.params(":name") + "!"));
		});
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
