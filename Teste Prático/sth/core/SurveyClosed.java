package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyClosed extends SurveyState {
    public SurveyClosed(Survey s) {
        super(s);
    }

    public String getName() {
        return "(fechado)";
    }

}