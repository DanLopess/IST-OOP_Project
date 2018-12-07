package sth.core;

import sth.core.Survey;

abstract class SurveyState implements java.io.Serializable {
    private Survey _survey;
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201810051538L;
    public SurveyState(Survey survey) { 
        _survey = survey; 
    }

    abstract String getName();

    String getStatus() { 
        return getClass().getName(); 
    }
}