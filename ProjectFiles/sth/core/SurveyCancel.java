package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyCancel extends SurveyState {
    public SurveyCancel(Survey s) {
        super(s);
    }

    public String getName() {
        return "(cancelado)";
    }

}