package jar.toni.sample.restexpress.rest.server;

import org.restexpress.Request;
import org.restexpress.Response;

public class HelloWorldController {
	private static final String NAME_HEADER = "name";

	public Object create(Request request, Response response) {
		return read(request, response);
	}

	public Object delete(Request request, Response response) {
		return read(request, response);
	}

	public Hello read(Request request, Response response) {
		String name = request.getHeader(NAME_HEADER);
		Hello hello = new Hello("Hello " + name + "!");
		return hello;
	}

	public Object update(Request request, Response response) {
		return read(request, response);
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