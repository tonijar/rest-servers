package jar.toni.sample.spring.boot.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@EnableAutoConfiguration
public class App {
	@RequestMapping(path = "/hello/{name}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Hello home(@PathVariable("name") String name) {
		return new Hello("Hello " + name + "!");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	class Hello {
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
