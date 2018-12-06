package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.1. Create project.
 */
public class DoCreateProject extends sth.app.common.ProjectCommand {
	private Input<String> _discipline;
	private Input<String> _projectName;
  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
		_projectName = _form.addStringInput(Message.requestProjectName());
	}

  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		_form.parse();
		_receiver.createProject(_discipline.value(), _projectName.value());
  }

}
