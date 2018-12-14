package sth.core;

public class Notification implements java.io.Serializable  {
	private String _message;
  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	  
	public Notification (String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
}
