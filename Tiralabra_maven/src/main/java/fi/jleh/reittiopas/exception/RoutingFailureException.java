package fi.jleh.reittiopas.exception;

public class RoutingFailureException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoutingFailureException (String message) {
		super(message);
	}
}
