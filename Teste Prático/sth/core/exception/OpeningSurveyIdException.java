package sth.core.exception;

/**
 *
 */
public class OpeningSurveyIdException extends Exception {
  private String _discipline;
  private String _project;
  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public OpeningSurveyIdException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return ("Opening Survey Exception: "+_discipline + " " +_project); 
  }

}
