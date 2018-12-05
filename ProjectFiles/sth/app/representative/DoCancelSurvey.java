package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import java.io.DialogException;
import sth.core.SchoolManager;
import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.SurveyFinishedException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.SurveyIdFinishedException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;


/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    try {
      _receiver.cancelSurvey(_discipline.value(), _project.value());
    } catch (NonEmptySurveyIdException nese){
      throw new NonEmptySurveyException(_discipline.value(), _project.value());
    } catch (NoSurveyIdException nse) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    } catch (SurveyIdFinishedException sfe) {
      throw new SurveyFinishedException(_discipline.value(), _project.value());
    }
  }

}
