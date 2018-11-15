package sth.core;

public class Project {
	private String _name;
	private String _description;
	private boolean _closed;
	private Survey _survey;

	public Project(String name, ) {
		_name = name;
		_description = description;
		_closed = false;
	}

	void close() {
		_closed = true;
	}

	Survey getSurvey() {
		return _survey;
	}

	public String getName() {
		return _name;
	}
}
