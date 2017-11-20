package jar.toni.sample.restexpress.rest.server;

import org.restexpress.Request;
import org.restexpress.exception.BadRequestException;

/**
 * @author toddf
 * @since Jan 11, 2012
 */
public abstract class AbstractDelayingController {
	private static final String TIMEOUT_MILLIS_HEADER = "delay_ms";

	protected long delay(Request request) {
		long millis = 0l;

		try {
			millis = Long.valueOf(request.getHeader(TIMEOUT_MILLIS_HEADER));
		} catch (NumberFormatException e) {
			throw new BadRequestException(e.getMessage());
		}

		if (millis == 0l)
			return 0l;

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return millis;
	}

}