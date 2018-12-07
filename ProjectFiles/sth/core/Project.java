package sth.core;

import sth.core.Survey;
import java.util.*;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.SurveyIdFinishedException;


public class Project implements java.io.Serializable {
	private String _name;
	private String _description;
	private boolean _closed;
	private Survey _survey;
	private Map<Integer, Submission> _submissions;
  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	  
	public Project(String name) {
		_name = name;
		_closed = false;
		_submissions = new HashMap<Integer,Submission>();
		_survey = null;
	}

	public Project(String name, String description) {
		_name = name;
		_description = description;
		_closed = false;
		_submissions = new HashMap<Integer,Submission>();
		_survey = null;
	}

	void close() {
		_closed = true;
	}

	boolean isClosed() {
		return _closed;
	}

	void addSubmission (int id, Submission sub) throws NoSuchProjectIdException {
		if(!_closed){
			_submissions.put(id, sub);			
		} else {
			throw new NoSuchProjectIdException("Project closed: " + _name);
		}
	}

	// returns true if student made at least 1 submission
	boolean studentSubmited(int id) {
		return (_submissions.containsKey(id));
	}

	Map<Integer, Submission> getSubmissions() {
		return _submissions;
	}

	void answerSurvey(int hours, String comment) throws NoSurveyIdException {
		if (_survey != null) {
			_survey.addAnswer(new Answer(comment, hours));
		} else {
			throw new NoSurveyIdException("","");
		}
	}

	void createSurvey() throws DuplicateSurveyIdException, NoSuchProjectIdException {
		if (!_closed){
			if (_survey == null) {
				_survey = new Survey();
			} else {
				throw new DuplicateSurveyIdException("","");
			}
		} else {
			throw new NoSuchProjectIdException("");
		}
		
	}

	void cancelSurvey() throws SurveyIdFinishedException, NoSurveyIdException {
		if (_survey != null) {
			_survey.cancel();
		} else {
			throw new NoSurveyIdException("","");
		}
	}

	void openSurvey() throws NoSurveyIdException {
		if (_survey != null) {
			_survey.open();
		} else {
			throw new NoSurveyIdException("","");
		}
	}

	void closeSurvey() {
		if (_survey != null) {
			_survey.close();
		} else {
			throw new NoSurveyIdException("","");
		}
	}

	void finishSurvey() {
		if (_survey != null) {
			_survey.finish();
		} else {
			throw new NoSurveyIdException("","");
		}
	}

	String showSurveys() {
		return null; // TODO
	}

	Survey getSurvey() {
		return _survey;
	}

	public String getName() {
		return _name;
	}
}
