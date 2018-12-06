package sth.core;

public class Answer {
	private String _message;
	private int _hours;

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
