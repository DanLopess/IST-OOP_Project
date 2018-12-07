package sth.core;

public class Submission implements java.io.Serializable {
	private String _message;
	private int _id;
  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	  
	public Submission(String message, int id) {
		_message = message;
		_id = id;
	}

	public String toString() {
		return ("* "+ _id + " - " + _message);
	}

	int getStudentId() {
		return _id;
	}

}
