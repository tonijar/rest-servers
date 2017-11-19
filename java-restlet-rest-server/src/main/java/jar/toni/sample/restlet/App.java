package jar.toni.sample.restlet;

import org.restlet.Component;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Hello world!
 *
 */
public class App extends ServerResource {
	public static void main(String[] args) throws Exception {
		// Create a new Restlet component and add a HTTP server connector to it
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8182);

		// Then attach it to the local host
		component.getDefaultHost().attach("/hello/{name}", App.class);

		// Now, let's start the component!
		// Note that the HTTP server connector is also automatically started.
		component.start();
	}

	@Get
	public Representation response() {
		JacksonRepresentation<Hello> jr = new JacksonRepresentation<Hello>(
				new Hello("Hello " + getAttribute("name") + "!"));
		jr.setMediaType(MediaType.APPLICATION_JSON);
		return jr;
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
