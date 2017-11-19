package jar.toni.sample.dropwizard.rest.server;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jar.toni.sample.dropwizard.rest.server.App.HelloWorldConfiguration;

/**
 * Hello world!
 *
 */
public class App extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) {
		final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(resource);
	}

	public static class HelloWorldConfiguration extends Configuration {
		@NotEmpty
		private String template;

		@NotEmpty
		private String defaultName = "Stranger";

		@JsonProperty
		public String getTemplate() {
			return template;
		}

		@JsonProperty
		public void setTemplate(String template) {
			this.template = template;
		}

		@JsonProperty
		public String getDefaultName() {
			return defaultName;
		}

		@JsonProperty
		public void setDefaultName(String name) {
			this.defaultName = name;
		}
	}

	public static class Hello {
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

	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public static class HelloWorldResource {
		private final String template;
		private final String defaultName;

		public HelloWorldResource(String template, String defaultName) {
			this.template = template;
			this.defaultName = defaultName;
		}

		@GET
		public Hello hello(@QueryParam("name") Optional<String> name) {
			final String value = String.format(template, name.orElse(defaultName));
			return new Hello(value);
		}
	}
}
