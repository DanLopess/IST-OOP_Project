package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyOpen extends SurveyState {

    public SurveyOpen (Survey s) { 
        super(s);
    }

    String getName() {
        return "open";
    }
}