package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import sth.core.Person;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSuchDisciplineException;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;


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
	_display.clear();
  	try {
		String s = _receiver.getDisciplineStudents(_disciplineName.value());
		_display.add(s);
		_display.display();
  	} catch (NoSuchDisciplineIdException e) {
	  	throw new NoSuchDisciplineException(_disciplineName.value());
  	}
  }

}
