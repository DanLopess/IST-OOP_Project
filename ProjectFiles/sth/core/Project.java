package sth.core;

import sth.core.Survey;

public class Project {
	private String _name;
	private String _description;
	private boolean _closed;
	private Survey _survey; 		// TODO in final version

	public Project(String name, String description) {
		_name = name;
		_description = description;
		_closed = false;
	}

	void close() {
		_closed = true;
	}

	void addSubmission (Student s, String message) {
		// TODO in final version
	}

	Collection<Submission> getSubmission() {
		// TODO in final version

	}

	Survey getSurvey() {
		// TODO in final version

	}

	public String getName() {
		return _name;
	}
}
