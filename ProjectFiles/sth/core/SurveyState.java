package sth.core;

import sth.core.Survey;

abstract class SurveyState {
    private Survey _survey;

    public SurveyState(Survey survey) { 
        _survey = survey; 
    }

    abstract String getName();

    String getStatus() { 
        return getClass().getName(); 
    }
}