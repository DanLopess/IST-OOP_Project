package sth.core.exception;

/**
 *
 */
public class DuplicateSurveyIdException extends Exception {
  private String _discipline;
  private String _project;

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public DuplicateSurveyIdException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return ("Duplicate Survey Exception: "+_discipline + " " +_project); 
  }

}
