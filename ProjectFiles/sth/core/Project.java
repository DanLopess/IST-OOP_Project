package sth.core;

import sth.core.Survey;
import java.util.*;
import sth.core.exception.NoSuchProjectIdException;

public class Project {
	private String _name;
	private String _description;
	private boolean _closed;
	private Survey _survey;
	private Map<Integer, Submission> _submissions;

	public Project(String name) {
		_name = name;
		_closed = false;
		_submissions = new HashMap<Integer,Submission>();
	}

	public Project(String name, String description) {
		_name = name;
		_description = description;
		_closed = false;
	}

	void close() {
		_closed = true;
	}

	void addSubmission (int id, Submission sub) throws NoSuchProjectIdException {
		if(!_closed){
			_submissions.put(id, sub);			
		} else {
			throw new NoSuchProjectIdException("Project closed: " + _name);
		}
	}

	void createSurvey() {

	}

	void answerSurvey(int hours) {
		if (_survey.getState() instanceof SurveyOpen)
			_survey.addAnswer(hours);
	}

	Map<Integer, Submission> getSubmissions() {
		return _submissions;
	}

	Survey getSurvey() {
		return _survey;
	}

	// returns true if student made at least 1 submission
	boolean studentSubmited(int id) {
		return (_submissions.containsKey(id));
	}

	public String getName() {
		return _name;
	}
}
