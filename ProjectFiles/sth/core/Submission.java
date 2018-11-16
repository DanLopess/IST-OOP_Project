package sth.core;

public class Submission {
	private String _message;
	private int _sId;

	public Submission(String message, Student s) {
		_message = message;
		_sId = s.getId();
	}

	public String toString() {
		// TODO in final version
		return null;
	}

	public int getStudentId() {
		return _sId;
	}

}
