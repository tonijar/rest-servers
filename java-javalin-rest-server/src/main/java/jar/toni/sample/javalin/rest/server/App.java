package jar.toni.sample.javalin.rest.server;

import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Javalin app = Javalin.start(7000);
		app.get("/hello/:name", ctx -> ctx.json(new Hello("Hello " + ctx.param("name") + "!")).status(200)
				.contentType("application/json"));
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
