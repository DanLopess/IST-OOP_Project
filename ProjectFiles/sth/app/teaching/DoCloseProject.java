package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.2. Close project.
 */
public class DoCloseProject extends sth.app.common.ProjectCommand {
	private Input<String> _discipline;
	private Input<String> _projectName;

  /**
   * @param receiver
   */
  public DoCloseProject(SchoolManager receiver) {
    super(Label.CLOSE_PROJECT, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
		_projectName = _form.addStringInput(Message.requestProjectName());
  }

  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		_form.parse();
		_receiver.DoCloseProject(_discipline.value(), _projectName.value());
  }

}
