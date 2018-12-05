package sth.core;

public class Submission {
	private String _message;
	private int _id;

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
