package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.ClosingSurveyException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCloseSurvey(SchoolManager receiver) {
    super(Label.CLOSE_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    try {
      _receiver.closeSurvey(_discipline.value(), _project.value());
    } catch (NoSurveyIdException nse) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    } catch (ClosingSurveyIdException cse) {
      throw new ClosingSurveyException(_discipline.value(), _project.value());
    }
  }

}
