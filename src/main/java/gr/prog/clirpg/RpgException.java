package gr.prog.clirpg;

/**
 * Game specific Exception
 */
public class RpgException extends RuntimeException {
	public RpgException(String message) {
		super(message);
	}

	public RpgException(String message, Throwable cause) {
		super(message, cause);
	}

	public RpgException(Throwable cause) {
		super(cause);
	}
}
