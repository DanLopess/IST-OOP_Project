package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.app.exception.NoSurveyException;

/**
 * 4.5.3. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, NoSurveyException {
    try {
      _display.clear();
      _display.add(_receiver.getSurveyResults(_discipline.value(), _project.value()));
      _display.display();
    } catch (NoSurveyIdException e) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
  }

}
