package fi.jleh.reittiopas.exception;

public class XMLParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Exception caused;
	
	
	public XMLParsingException (String message, Exception e) {
		super(message);
		
		this.caused = e;
	}
	
	public Exception getCaused() {
		return caused;
	}

	public void setCaused(Exception caused) {
		this.caused = caused;
	}
}
