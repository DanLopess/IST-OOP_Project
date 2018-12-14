package sth.core;

import sth.core.SurveyState;
import sth.core.SurveyClosed;
import sth.core.SurveyOpen;
import sth.core.SurveyFinished;
import sth.core.Person;
import sth.core.Answer;
import sth.core.exception.SurveyIdFinishedException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.FinishingSurveyIdException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class Survey implements java.io.Serializable {
	private List<Answer> _answers;
    private SurveyState _state;
	
	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	
	Survey() {
		_answers = new ArrayList<Answer>();
		_state = new SurveyCreated(this);
	}

	void setState(SurveyState state) { 
		_state = state; 
	}
	
	SurveyState getState() {
		return _state;
	}

	int getNAnswers() {
		return _answers.size();
	}

	void open() throws OpeningSurveyIdException {
		if (_state instanceof SurveyClosed || _state instanceof SurveyCreated)
			_state = new SurveyOpen(this);
		else {
			throw new OpeningSurveyIdException("","");
		}
	}

	void close() throws ClosingSurveyIdException {
		if (_state instanceof SurveyOpen)
			_state = new SurveyClosed(this);
		else if (_state instanceof SurveyClosed) {
			// Do nothing
		}
		else {
			throw new ClosingSurveyIdException("","");
		}
	}

	void finish() throws FinishingSurveyIdException {
		if(_state instanceof SurveyClosed) {
			_state = new SurveyFinished(this);
		} else {
			throw new FinishingSurveyIdException("","");
		}
	}

	void cancel() throws SurveyIdFinishedException, NonEmptySurveyIdException, OpeningSurveyIdException {
		if (this.getState() instanceof SurveyClosed)
			this.open();
		else if (_state instanceof SurveyFinished)
			throw new SurveyIdFinishedException("","");
		else if (_state instanceof SurveyOpen && _answers.size()>0) {
			throw new NonEmptySurveyIdException("","");
		}
	}

	void addAnswer(Answer a) throws NoSurveyIdException{
		if(_state instanceof SurveyOpen){
			_answers.add(a);
		}
		else
			throw new NoSurveyIdException("","");
	}

	String getTimes(boolean detailed) {
		Iterator<Answer> iterator = _answers.iterator();
		int min=0;
		int max = 0;
		int sum = 0;

		if(iterator.hasNext())
			min = iterator.next().getHours(); // initialize min

		while(iterator.hasNext()) {
			int i = iterator.next().getHours();
			if (i > max)
				max = i;
			if (i < min)
				min = i;
			sum+=i;
		}
		if (sum != 0){
			int average = ((int)(sum/_answers.size()));
			if (detailed)
				return min + ", "+ average + ", " + max;
			else
				return "" + average;
		}
		else
			return "0, 0, 0";
	}

	String printAnswers(boolean detailed) {
		if (detailed)
			return " * Número de respostas: " + _answers.size() + "\n " + " * Tempo médio (horas) (mínimo, médio, máximo): " + this.getTimes(true);
		else {
			return " * Número de respostas: " + _answers.size() + "\n " + " * Tempo médio (horas) (mínimo, médio, máximo): " + this.getTimes(false);
		}
	}

@Override
	public String toString() {
		return _state.getName();
	}
}
