package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;

/**
 * 4.5.2. Answer survey.
 */
public class DoAnswerSurvey extends sth.app.common.ProjectCommand {
  private Input<Integer> _hours;
  private Input<String> _comment;


  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    _hours = _form.addIntegerInput(Message.requestProjectHours());
    _comment = _form.addStringInput(Message.requestComment());
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException {
    try {
      _receiver.fillSurvey(_discipline.value(), _project.value(), _hours.value(), _comment.value());
    } catch (NoSurveyIdException nse) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    } catch (NoSuchDisciplineIdException nsde) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSuchProjectIdException nspe) {
      throw new NoSuchProjectException(_discipline.value(), _project.value());
    }
  }

}
