package sth.core.exception;

/**
 *
 */
public class SurveyIdFinishedException extends Exception {
  private String _discipline;
  private String _project;
  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public SurveyIdFinishedException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return ("Survey Finished Exception: "+_discipline + " " +_project); 
  }

}
