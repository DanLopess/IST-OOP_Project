package sth.core.survey;

import sth.core.survey.SurveyState;

public class Survey {
    private SurveyState _state = new SurveyClosed(this);
    protected void setState(SurveyState state) { _state = state; }
  
    public void click()    { _state.click();    }
    public void complete() { _state.complete(); }
    public void timeout()  { _state.timeout();  }
  
	public void status() { System.out.println(_state.status()); }
	
	// TODO in final version

	public Survey() {
		// TODO in final version
	}

	void open() {
		// TODO in final version
	}

	void close() {
		// TODO in final version
	}

	//void finalize() {
		// TODO in final version
	//}

	void addAnswer() {
		// TODO in final version
	}

	String getResults() {
		// TODO in final version
		return null;
	}

}
