package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyCreated extends SurveyState {

    public SurveyCreated (Survey s) { 
        super(s);
    }

    String getName() {
        return "(por abrir)";
    }
}