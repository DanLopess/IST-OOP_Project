package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class DuplicateProjectIdException extends Exception {
  /** Project id. */
  private String _projectName;
  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public DuplicateProjectIdException(String project) {
    _projectName = project;
  }

  /** @return id */
  public String getId() {
    return _projectName;
  }

}
