package sth.core;

import sth.core.Survey;
import java.util.List;
import java.util.ArrayList;

public class Project {
	private String _name;
	private String _description;
	private boolean _closed;
	private Survey _survey;

	public Project(String name) {
		_name = name;
		_closed = false;
	}

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

	List<Submission> getSubmissions() {
		// TODO in final version
		return null;
	}

	Survey getSurvey() {
		// TODO in final version
		return null;
	}

	public String getName() {
		return _name;
	}
}
