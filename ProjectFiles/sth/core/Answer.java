package sth.core;

public class Answer implements java.io.Serializable{
	private String _message;
	private int _hours;
  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	  
	public Answer(String message, int hours) {
		_message = message;
		_hours = hours;
	}

	public Answer(int hours) {
		_message = null;
		_hours = hours;
	}

	String getMessage() {
		return _message;
	}

	int getHours() {
		return _hours;
	}

}
