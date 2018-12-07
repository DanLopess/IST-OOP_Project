package sth.core;

import sth.core.SurveyState;
import sth.core.SurveyClosed;
import sth.core.SurveyOpen;
import sth.core.SurveyFinished;
import sth.core.Person;
import sth.core.Answer;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class Survey implements java.io.Serializable {
	private List<Answer> _answers;
	private int _nAnswers;
    private SurveyState _state = new SurveyClosed(this);
	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	Survey() {}

	void setState(SurveyState state) { 
		_state = state; 
	}
	
	SurveyState getState() {
		return _state;
	}

	int getNAnswers() {
		return _nAnswers;
	}

	void open() {
		if (!(_state instanceof SurveyFinished))
			_state = new SurveyOpen(this);
	}

	void close() {
		// TODO in final version
	}

	void finish() {
		// TODO in final version
	}

	void addAnswer(Answer a) {
		if(_state instanceof SurveyOpen)
			_answers.add(a);
		
	}

	int getAverage() {
		Iterator<Answer> iterator = _answers.iterator();
		int sum = 0;

		while(iterator.hasNext()) {
			int i = iterator.next().getHours();
			sum+=i;
		}
		if (sum != 0)
			return ((int)(sum/_answers.size()));
		else
			return 0;
	}

@Override
	public String toString() {
		return ""; // (state) .. if open show open, if finished, show info, else show closed
	}
}
