package sth.core;

import sth.core.SurveyState;
import sth.core.Survey;

public class SurveyCreated extends SurveyState implements java.io.Serializable {

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;

    public SurveyCreated (Survey s) { 
        super(s);
    }

    String getName() {
        return "(por abrir)";
    }
}