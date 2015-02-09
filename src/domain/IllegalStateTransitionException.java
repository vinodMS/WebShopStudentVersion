package domain;

public class IllegalStateTransitionException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalStateTransitionException(String from, String to, String reason) {
		super("Illegal State transition: going from " + from + " to " + to + " is not allowed because " + reason + ".");
	}
}
