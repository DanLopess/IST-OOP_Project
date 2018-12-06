package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.*;
import sth.app.exception.NoSuchPersonException;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
	private Input<String> _string;
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
	_string = _form.addStringInput(Message.requestPersonName());
  }

  @Override
  public final void execute() throws NoSuchPersonException{
	_form.parse();
	_display.clear();

	String persons;
	persons = _receiver.searchPerson(_string.value());

	_display.add(persons);
	_display.display();
  }
}
