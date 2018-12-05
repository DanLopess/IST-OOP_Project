package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyFinished extends SurveyState {

    public SurveyFinished(Survey s) {
        super(s);
    }

    String getName() {
        return "";
    }
}