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


public class Survey {
	private List<Integer> _hours;
	private int _nrespostas;
    private SurveyState _state = new SurveyClosed(this);
	
	Survey() {}

	void setState(SurveyState state) { 
		_state = state; 
	}
	
	SurveyState getState() {
		return _state;
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

	void addAnswer(int hours) {
		if(_state instanceof SurveyOpen)
			_hours.add(hours);
		
	}

	int getAverage() {
		Iterator<Integer> iterator = _hours.iterator();
		int sum = 0;

		while(iterator.hasNext()) {
			int i = iterator.next();
			sum+=i;
		}
		if (sum != 0)
			return ((int)(sum/_hours.size()));
		else
			return 0;
	}
}
