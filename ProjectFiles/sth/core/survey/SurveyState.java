
package sth.core.survey;

abstract class SurveyState {
    protected Survey _survey;

    public SurveyState(Survey survey) { _survey = survey; }

    public abstract void click();

    public void timeout()  { }
    public void complete() { }

    public String status() { return getClass().getName(); }
 }