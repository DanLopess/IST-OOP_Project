package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Person;


//FIXME import other classes if needed

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {
  private Person _teacher;
	private Display _display;
	private Input<String> _disciplineName;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) throws  {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
		_disciplineName = _form.addStringInput(Message.requestDisciplineName());
		_display = new Display();
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
		_form.parse();
		_display.add(DoShowDisciplineStudents(_disciplineName));
		_display.display();
  }

}
