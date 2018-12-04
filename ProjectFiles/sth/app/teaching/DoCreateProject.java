package sth.app.teaching;

import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.1. Create project.
 */
public class DoCreateProject extends sth.app.common.ProjectCommand {
	private Input<String> _disciplineName;
	private Input<String> _projectName;
  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
		_disciplineName = _form.addStringInput(Message.requestDisciplineName());
		_projectName = _form.addStringInput(Message.requestProjectName());
	}

  @Override
  public final void myExecute() throws NoSuchDisciplineIdException {
		_form.parse();
		_receiver.DoCreateProject(_disciplineName.value(), _projectName.value());
  }

}
