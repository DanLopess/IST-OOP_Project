package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyClosed extends SurveyState implements java.io.Serializable {

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
    public SurveyClosed(Survey s) {
        super(s);
    }

    public String getName() {
        return "(fechado)";
    }

}