package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import sth.core.Person;


/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {
	private Input<String> _disciplineName;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
		_disciplineName = _form.addStringInput(Message.requestDisciplineName());
  }

  @Override
  public final void execute() throws DialogException {
		_form.parse();

		List<String> _persons;
		try {
			_persons = _receiver.DoShowDisciplineStudents(_disciplineName);
			Collections.sort(_persons);
			Iterator<String> iterator = _persons.iterator();

			while (iterator.hasNext()) {
				_display.addLine(iterator.next());
			}
			_display.display();

	} catch (NoSuchPersonException nspe) {
			_display.popup(Message.fileNotFound());
		}
	}

}
